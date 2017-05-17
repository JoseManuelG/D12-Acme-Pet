
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AnimaniacRepository;
import domain.Animaniac;

@Component
@Transactional
public class StringToAnimaniacConverter implements Converter<String, Animaniac> {

	@Autowired
	AnimaniacRepository	animaniacRepository;


	@Override
	public Animaniac convert(final String text) {
		Animaniac result;
		int id;

		try {
			id = Integer.valueOf(text);
			this.animaniacRepository.findAll();
			result = this.animaniacRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
