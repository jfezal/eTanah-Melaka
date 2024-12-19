package etanah.view;

import java.lang.reflect.Method;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

import com.google.inject.Binder;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;

import able.stripes.StripesModule;

public class etanahStripesModule extends StripesModule {
	
    public void configure(Binder binder) {
        super.configure(binder);

        /* TODO KIV: 
        Matcher<Method> stripesActionMatcher = Matchers.returns(Matchers.subclassesOf(Resolution.class))
                .or(Matchers.annotatedWith(DefaultHandler.class).or(Matchers.annotatedWith(HandlesEvent.class)));

        AuthRequiredIntereptor authRequiredIntereptor = new AuthRequiredIntereptor();
        binder.bindInterceptor(Matchers.any(),
                stripesActionMatcher.and(Matchers.annotatedWith(AuthRequired.class)),
                authRequiredIntereptor);

        binder.bindInterceptor(Matchers.annotatedWith(AuthRequired.class),
                stripesActionMatcher,
                authRequiredIntereptor);
        */

    }

}
