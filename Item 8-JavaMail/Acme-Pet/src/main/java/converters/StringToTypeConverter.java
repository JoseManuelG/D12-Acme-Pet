
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TypeRepository;
import domain.Type;

@Component
@Transactional
public class StringToTypeConverter implements Converter<String, Type> {

	@Autowired
	TypeRepository	typeRepository;


	@Override
	public Type convert(final String text) {
		Type result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.typeRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
