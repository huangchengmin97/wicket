/*
 * $Id$ $Revision:
 * 1.7 $ $Date$
 * 
 * ==================================================================== Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.markup.html.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import wicket.WicketRuntimeException;
import wicket.util.resource.IResource;
import wicket.util.resource.ResourceNotFoundException;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An image subclass that allows easy rendering of dynamic images. An image can
 * be set with setImage(BufferedImage) and its extension can be specified with
 * setExtension(String). After this, the image will be cached as an input stream
 * and will render as would any other Image resource.
 * 
 * @author Jonathan Locke
 */
public abstract class AbstractDynamicImage extends Image
{
	/** Serial Version ID */
	private static final long serialVersionUID = 5934721258765771884L;

	/** The image extension */
	private String extension = "jpeg";

	/**
	 * Constructor.
	 * 
	 * @param name
	 *            Component name
	 */
	public AbstractDynamicImage(String name)
	{
		super(name);
	}
	
	/**
	 * @return Returns the extension.
	 */
	public String getExtension()
	{
		return extension;
	}
	
	/**
	 * Sets the extension of this dynamic image
	 * 
	 * @param extension
	 *            The extension to set.
	 */
	public void setExtension(String extension)
	{
		this.extension = extension;
	}

	/**
	 * @return The image data for this dynamic image
	 */
	protected abstract byte[] getImageData();
	
	/**
	 * @param image The image to turn into data
	 * @return The image data for this dynamic image
	 */
	protected byte[] toImageData(final BufferedImage image)
	{
		try
		{
			// Create output stream
			final ByteArrayOutputStream out = new ByteArrayOutputStream();

			// Get image writer for extension
			final ImageWriter writer = (ImageWriter)ImageIO.getImageWritersByFormatName(extension)
					.next();

			// Write out gif
			writer.setOutput(ImageIO.createImageOutputStream(out));
			writer.write(image);

			// Return the image data
			return out.toByteArray();
		}
		catch (IOException e)
		{
			throw new WicketRuntimeException("Unable to convert dynamic image to stream", e);
		}
	}
	
	/**
	 * @return Gets the image resource to attach to the component.
	 */
	protected IResource getResource()
	{
		return new IResource()
		{
			private InputStream inputStream = null;

			public void close() throws IOException
			{
				if (inputStream != null)
                {
					inputStream.close();
                }
			}

			public String getExtension()
			{
				return extension;
			}

			public InputStream getInputStream() throws ResourceNotFoundException
			{
				if (inputStream == null)
				{
					inputStream = new ByteArrayInputStream(getImageData());
				}
				return inputStream;
			}
		};
	}
}


