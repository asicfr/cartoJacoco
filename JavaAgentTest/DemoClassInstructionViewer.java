package test;

import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.LinkedList;

import org.jacoco.core.internal.instr.InstrSupport;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.InstructionAdapter;

public class DemoClassInstructionViewer {

	public static class MethodPrinterVisitor extends ClassVisitor {

		public MethodPrinterVisitor(final int api) {
			super(api);
		}

		@Override
		public MethodVisitor visitMethod(final int access, final String name,
				final String desc, final String signature,
				final String[] exceptions) {
			try {
				final LinkedList<String> parameters;
				final boolean isStaticMethod;

				final Type[] args = Type.getArgumentTypes(desc);
				final Type ret = Type.getReturnType(desc);

				parameters = new LinkedList<String>();
				isStaticMethod = Modifier.isStatic(access);

				return new MethodVisitor(Opcodes.ASM5) {
					// assume static method until we get a first parameter name
					@Override
					public void visitLocalVariable(final String name,
							final String description, final String signature,
							final Label start, final Label end,
							final int index) {
						if (isStaticMethod && parameters.size() < args.length) {
							parameters.add(
									args[index].getClassName() + " " + name);
						} else if (index > 0
								&& parameters.size() < args.length) {
							// for non-static the 0th arg is "this" so we need
							// to offset by -1
							parameters.add(args[index - 1].getClassName() + " "
									+ name);
						}
					}

					@Override
					public void visitEnd() {
						System.out.println("Method: " + ret.getClassName() + " "
								+ name + "(" + String.join(", ", parameters)
								+ ")");
						super.visitEnd();
					}
				};
			} catch (final Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		/*
		@Override
		public MethodVisitor visitMethod(final int access, final String name,
				final String desc, final String signature,
				final String[] exceptions) {

			System.out.println("\n" + name + desc);

			final MethodVisitor oriMv = new MethodVisitor(4) {
			};
			// An instructionAdapter is a special MethodVisitor that
			// lets us process instructions easily
			final InstructionAdapter instMv = new InstructionAdapter(oriMv) {

				@Override
				public void visitInsn(final int opcode) {
					System.out.println(opcode);
					super.visitInsn(opcode);
				}

			};
			return instMv;

		}
		*/
	}

	private static InputStream getTargetClass(final String name) {
		final String resource = '/' + name.replace('.', '/') + ".class";
		return DemoClassInstructionViewer.class.getResourceAsStream(resource);
	}

	public static void main(final String[] args) throws Exception {
		final String targetName = TestTarget.class.getName();
		final InputStream in = getTargetClass(targetName);
		final ClassReader classReader = new ClassReader(in);
		classReader.accept(
				new MethodPrinterVisitor(InstrSupport.ASM_API_VERSION), 0);

	}

}