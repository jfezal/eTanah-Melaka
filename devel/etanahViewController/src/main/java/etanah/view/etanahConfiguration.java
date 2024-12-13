package etanah.view;

import able.guice.configuration.ConfigurationModule;
import java.util.TimeZone;

public class etanahConfiguration extends ConfigurationModule {

	@Override
	protected void bindConfiguration() {
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
	}

}
