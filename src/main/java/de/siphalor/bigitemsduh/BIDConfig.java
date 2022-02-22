package de.siphalor.bigitemsduh;

import de.siphalor.tweed4.annotated.AConfigConstraint;
import de.siphalor.tweed4.annotated.AConfigEntry;
import de.siphalor.tweed4.annotated.ATweedConfig;
import de.siphalor.tweed4.config.ConfigEnvironment;
import de.siphalor.tweed4.config.ConfigScope;
import de.siphalor.tweed4.config.constraints.RangeConstraint;

@ATweedConfig(scope = ConfigScope.SMALLEST, environment = ConfigEnvironment.CLIENT, tailors = "tweed4:coat")
public class BIDConfig {
	@AConfigEntry(comment = "Sets where the zoomed item will be displayed on the screen")
	public static HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;

	@AConfigEntry(
			constraints = @AConfigConstraint(value = RangeConstraint.class, param = "0.05..1"),
			comment = "Sets the how big the zoom will be, relative to the space available on the screen"
	)
	public static float scale = 0.8F;
}
