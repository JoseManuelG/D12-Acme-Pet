
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AttributeRepository;
import domain.Attribute;

@Component
@Transactional
public class StringToAttributeConverter implements Converter<String, Attribute> {

	@Autowired
	AttributeRepository	attributeRepository;


	@Override
	public Attribute convert(final String text) {
		Attribute result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.attributeRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
