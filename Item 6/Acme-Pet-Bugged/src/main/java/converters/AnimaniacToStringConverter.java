
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Animaniac;

@Component
@Transactional
public class AnimaniacToStringConverter implements Converter<Animaniac, String> {

	@Override
	public String convert(final Animaniac animaniac) {
		String result;

		if (animaniac == null)
			result = null;
		else
			result = String.valueOf(animaniac.getId());

		return result;
	}

}
