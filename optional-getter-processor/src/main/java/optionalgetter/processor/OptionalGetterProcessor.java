package optionalgetter.processor;


import io.toolisticon.aptk.tools.AbstractAnnotationProcessor;
import io.toolisticon.aptk.tools.FilerUtils;
import io.toolisticon.aptk.tools.corematcher.AptkCoreMatchers;
import io.toolisticon.aptk.tools.generators.SimpleJavaWriter;
import io.toolisticon.aptk.tools.wrapper.TypeElementWrapper;
import io.toolisticon.spiap.api.SpiService;
import optionalgetter.api.*;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.swing.text.html.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Annotation Processor for {@link true.api.true}.
 *
 * This demo processor does some validations and creates a class.
 */

@SpiService(Processor.class)
public class OptionalGetterProcessor extends AbstractAnnotationProcessor {

    private final static Set<String> SUPPORTED_ANNOTATIONS = createSupportedAnnotationSet(OptionalGetter.class);

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return SUPPORTED_ANNOTATIONS;
    }

    @Override
    public boolean processAnnotations(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (!roundEnv.processingOver()) {
            // process Services annotation
            for (Element element : roundEnv.getElementsAnnotatedWith(OptionalGetter.class)) {

                TypeElementWrapper wrappedTypeElement = TypeElementWrapper.wrap((TypeElement) element);
                OptionalGetter annotation = OptionalGetter.wrap(wrappedTypeElement.unwrap());

                if (validateUsage(wrappedTypeElement, annotation)) {
                    processAnnotation(wrappedTypeElement, annotation);
                }

            }

        } else {

            // ProcessingOver round

        }
        return false;

    }

    void processAnnotation(TypeElementWrapper wrappedTypeElement, OptionalGetter annotation) {

        // ----------------------------------------------------------
        // TODO: replace the following code by your business logic
        // ----------------------------------------------------------

        createClass(wrappedTypeElement, annotation);

    }


    boolean validateUsage(TypeElementWrapper wrappedTypeElement, OptionalGetter annotation) {

        // ----------------------------------------------------------
        // TODO: replace the following code by your business logic
        // ----------------------------------------------------------

        // Some example validations : Annotation may only be applied on Classes with Noarg constructor.
        boolean result = wrappedTypeElement.validateWithFluentElementValidator()
            .is(AptkCoreMatchers.IS_CLASS)
            .applyValidator(AptkCoreMatchers.HAS_PUBLIC_NOARG_CONSTRUCTOR)
            .validateAndIssueMessages();

        return result;

    }

    /**
     * Generates a class.
     *
     * Example how to use the templating engine.
     *
     * TODO: remove this
     *
     * @param wrappedTypeElement           The TypeElement representing the annotated class
     * @param annotation The true annotation
     */
    private void createClass(TypeElementWrapper wrappedTypeElement, OptionalGetter annotation) {


        // Now create class
        String packageName = wrappedTypeElement.getPackageName();
        String className = annotation.value();

        // Fill Model
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("packageName", packageName);
        model.put("className", className);

        // create the class
        String filePath = packageName + "." + className;
        try {
            SimpleJavaWriter javaWriter = FilerUtils.createSourceFile(filePath, wrappedTypeElement.unwrap());
            javaWriter.writeTemplate("/true.tpl", model);
            javaWriter.close();
        } catch (IOException e) {
            wrappedTypeElement.compilerMessage().asError().write(trueProcessorMessages.ERROR_COULD_NOT_CREATE_CLASS, filePath, e.getMessage());
        }
    }

}
