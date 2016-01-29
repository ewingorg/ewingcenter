package extend.cache.console;

/**
 * @author tanson lam 2014年9月2日
 *
 */
interface ConsoleCommand {

    public abstract boolean execute(Object[] params) throws Exception;

    public abstract String description();

    public abstract String command();

    public abstract String help();

}
