package test;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtField;

public class Transformer implements ClassFileTransformer {

	@Override
	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		return visitClass(className, classBeingRedefined, classfileBuffer);

	}

	private byte[] visitClass(String className, Class<?> classBeingRedefined, byte[] classfileBuffer) {
		ClassPool pool = ClassPool.getDefault();
		CtClass cl = null;
		try {
			cl = pool.makeClass(new java.io.ByteArrayInputStream(classfileBuffer));
			if (cl.isInterface() == false) {
				if (cl.getPackageName().startsWith("test")) {
					// TODO filtrer sur les classes appartenant à une liste de package
					
					// Ajout d'un champ static dans la classe
					CtField field = CtField.make("private static long _instanceCount;", cl);
	
					cl.addField(field);
	
					CtBehavior[] constructors = cl.getDeclaredConstructors();
					for (int i = 0; i < constructors.length; i++) {
						// On incrémente le compteur et on l'affiche
						constructors[i].insertAfter("_instanceCount++;");
						constructors[i].insertAfter("System.out.println(\"" + className + " : \" + _instanceCount);");
	
					}
	
					// Génération du bytecode modifié
					classfileBuffer = cl.toBytecode();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (cl != null) {
				cl.detach();
			}
		}
		return classfileBuffer;
	}
}
