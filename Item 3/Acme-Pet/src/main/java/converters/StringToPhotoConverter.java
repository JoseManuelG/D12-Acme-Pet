
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PhotoRepository;
import domain.Photo;

@Component
@Transactional
public class StringToPhotoConverter implements Converter<String, Photo> {

	@Autowired
	PhotoRepository	photoRepository;


	@Override
	public Photo convert(final String text) {
		Photo result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.photoRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
