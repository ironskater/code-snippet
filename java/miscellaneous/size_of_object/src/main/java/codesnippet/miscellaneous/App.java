package codesnippet.miscellaneous;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

public class App
{
	public static class Course
	{
		private String name;
		// constructor
		public Course() {}
		public Course(String name) {
			this.name = name;
		}
	}

	public static class Professor
	{
		private String name;
		private boolean tenured;
		private List<Course> courses = new ArrayList<>();
		private int level;
		private LocalDate birthDay;
		private double lastEvaluation;

		// constructor
	}

	public static void
		main(String[] args)
	{
		String ds = "a";
		Course course = new Course(ds);

		/**
		 * 12-bytes obj header + 4-bytes String obj reference
		 */
		System.out.println(VM.current().sizeOf(course)); // 16 byte

		/**
		 * OFF  SZ     TYPE DESCRIPTION               VALUE
			 0   8          (object header: mark)     0x00000079a113f601 (hash: 0x79a113f6; age: 0)
			 8   4          (object header: class)    0x00065f2f
			12   4   byte[] String.value              [97]
			16   4      int String.hash               97
			20   1     byte String.coder              0
			21   3          (object alignment gap)
			Instance size: 24 bytes
		 */
		System.out.println(VM.current().sizeOf(ds)); // 24 byte


		/**
		 * OFF  SZ   TYPE DESCRIPTION               VALUE
			 0   8        (object header: mark)     0x0000000000000001 (non-biasable; age: 0)
			 8   4        (object header: class)    0x000793d5
			12   4        (array length)            1
			12   4        (alignment/padding gap)
			16   1   byte [B.<elements>             N/A
			17   7        (object alignment gap)
			Instance size: 24 bytes
		 */
		System.out.println(VM.current().sizeOf(ds.getBytes())); // 24 byte

		System.out.println(GraphLayout.parseInstance(course).totalSize()); // 64 bytes
	}
}
