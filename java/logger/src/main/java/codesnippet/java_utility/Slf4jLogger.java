package codesnippet.java_utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

public final class Slf4jLogger
{
	private static final boolean locationFlg = false;

	private Logger logger;

	public Slf4jLogger(Class<?> clazz)
	{
		logger = LoggerFactory.getLogger(clazz);
	}

	public void
		error(	String format,
				Object... arguments)
	{
		if (!logger.isErrorEnabled())
		{
			return;
		}

		if (!locationFlg)
		{
			logger.error(	format,
							arguments);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] st = new Throwable().getStackTrace();

		if (st.length > 1)
		{
			className = st[1].getClassName();
			methodName = st[1].getMethodName();
			lineNumber = st[1].getLineNumber();

		}

		logger.error(	"[{}::{}({})] : {}",
						className,
						methodName,
						lineNumber,
						MessageFormatter.arrayFormat(	format,
														arguments)
										.getMessage());
	}

	public void
		error(	Throwable thr,
				String format,
				Object... arguments)
	{
		if (!logger.isErrorEnabled())
		{
			return;
		}

		String msg = MessageFormatter.arrayFormat(	format,
													arguments).getMessage();

		if (!locationFlg)
		{
			logger.error(	msg,
							thr);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] se = new Throwable().getStackTrace();

		if (se.length > 1)
		{
			className = se[1].getClassName();
			methodName = se[1].getMethodName();
			lineNumber = se[1].getLineNumber();
		}

		logger.error(MessageFormatter.arrayFormat(	"[{}::{}({})] : {}",
													new Object[]
													{
														className,
														methodName,
														lineNumber,
														msg
													})
									.getMessage(),
					thr);
	}

	public void
		warn(	String format,
				Object... arguments)
	{
		if (!logger.isWarnEnabled())
		{
			return;
		}

		if (!locationFlg)
		{
			logger.warn(format,
						arguments);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] st = new Throwable().getStackTrace();

		if (st.length > 1)
		{
			className = st[1].getClassName();
			methodName = st[1].getMethodName();
			lineNumber = st[1].getLineNumber();

		}

		logger.warn("[{}::{}({})] : {}",
					className,
					methodName,
					lineNumber,
					MessageFormatter.arrayFormat(	format,
													arguments)
									.getMessage());
	}

	public void
		warn(	Throwable thr,
				String format,
				Object... arguments)
	{
		if (!logger.isWarnEnabled())
		{
			return;
		}

		String msg = MessageFormatter.arrayFormat(	format,
													arguments).getMessage();

		if (!locationFlg)
		{
			logger.warn(msg,
						thr);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] se = new Throwable().getStackTrace();

		if (se.length > 1)
		{
			className = se[1].getClassName();
			methodName = se[1].getMethodName();
			lineNumber = se[1].getLineNumber();
		}

		logger.warn(MessageFormatter.arrayFormat(	"[{}::{}({})] : {}",
													new Object[]
													{
														className,
														methodName,
														lineNumber,
														msg
													})
									.getMessage(),
					thr);
	}

	public void
		info(	String format,
				Object... arguments)
	{
		if (!logger.isInfoEnabled())
		{
			return;
		}

		if (!locationFlg)
		{
			logger.info(format,
						arguments);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] st = new Throwable().getStackTrace();

		if (st.length > 1)
		{
			className = st[1].getClassName();
			methodName = st[1].getMethodName();
			lineNumber = st[1].getLineNumber();

		}

		logger.info("[{}::{}({})] : {}",
					className,
					methodName,
					lineNumber,
					MessageFormatter.arrayFormat(	format,
													arguments)
									.getMessage());
	}

	public void
		info(	Throwable thr,
				String format,
				Object... arguments)
	{
		if (!logger.isInfoEnabled())
		{
			return;
		}

		String msg = MessageFormatter.arrayFormat(	format,
													arguments).getMessage();

		if (!locationFlg)
		{
			logger.info(msg,
						thr);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] se = new Throwable().getStackTrace();

		if (se.length > 1)
		{
			className = se[1].getClassName();
			methodName = se[1].getMethodName();
			lineNumber = se[1].getLineNumber();
		}

		logger.info(MessageFormatter.arrayFormat(	"[{}::{}({})] : {}",
													new Object[]
													{
														className,
														methodName,
														lineNumber,
														msg
													})
									.getMessage(),
					thr);
	}

	public void
		debug(	String format,
				Object... arguments)
	{
		if (!logger.isDebugEnabled())
		{
			return;
		}

		if (!locationFlg)
		{
			logger.debug(	format,
							arguments);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] st = new Throwable().getStackTrace();

		if (st.length > 1)
		{
			className = st[1].getClassName();
			methodName = st[1].getMethodName();
			lineNumber = st[1].getLineNumber();

		}

		logger.debug(	"[{}::{}({})] : {}",
						className,
						methodName,
						lineNumber,
						MessageFormatter.arrayFormat(	format,
														arguments)
										.getMessage());
	}

	public void
		debug(	Throwable thr,
				String format,
				Object... arguments)
	{
		if (!logger.isDebugEnabled())
		{
			return;
		}

		String msg = MessageFormatter.arrayFormat(	format,
													arguments).getMessage();

		if (!locationFlg)
		{
			logger.debug(	msg,
							thr);

			return;
		}

		String className = null;
		String methodName = null;
		int lineNumber = 0;

		StackTraceElement[] se = new Throwable().getStackTrace();

		if (se.length > 1)
		{
			className = se[1].getClassName();
			methodName = se[1].getMethodName();
			lineNumber = se[1].getLineNumber();
		}

		logger.debug(MessageFormatter.arrayFormat(	"[{}::{}({})] : {}",
													new Object[]
													{
														className,
														methodName,
														lineNumber,
														msg
													})
									.getMessage(),
					thr);
	}
}
