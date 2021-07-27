package de.skash.narutobot.feature;

import com.google.gson.Gson;
import de.skash.narutobot.core.cache.ServerCache;
import de.skash.narutobot.core.command.CommandHandler;
import de.skash.narutobot.core.command.MessageListener;
import de.skash.narutobot.core.model.Inventory;
import de.skash.narutobot.core.model.Server;
import de.skash.narutobot.core.networking.Routes;
import de.skash.narutobot.feature.commands.TestCommand;
import net.dv8tion.jda.api.GatewayEncoding;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.concurrent.TimeUnit;

public class Bot {
    public static String BOT_TOKEN_KEY = "NARUTO_BOT_TOKEN";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    public static Logger LOG = LoggerFactory.getLogger(Bot.class);
    private final OkHttpClient httpClient;
    private final Gson gson = new Gson();
    private final ServerCache serverCache = new ServerCache();
    private final CommandHandler commandHandler = new CommandHandler();

    private Bot() {
        httpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 10, TimeUnit.SECONDS))
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        serverCache.cacheElement(new Server(861541024035504138L, "?"));
        commandHandler.registerCommands(new TestCommand());

        LOG.info(Routes.SERVER_INDEX.getResponseType().getTypeName());


        try {
            JDABuilder.createDefault(System.getenv(BOT_TOKEN_KEY))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .addEventListeners(new MessageListener(this))
                    .setActivity(Activity.playing("Yes..."))
                    .setGatewayEncoding(GatewayEncoding.ETF)
                    .setMemberCachePolicy(MemberCachePolicy.NONE)
                    .disableCache(CacheFlag.ACTIVITY, CacheFlag.EMOTE,
                            CacheFlag.CLIENT_STATUS,
                            CacheFlag.MEMBER_OVERRIDES,
                            CacheFlag.ROLE_TAGS,
                            CacheFlag.VOICE_STATE
                    )
                    .build().awaitReady();
        } catch (final LoginException | InterruptedException e) {
            LOG.error("Failed to start Bot", e);
        }
    }

    public static void main(String[] args) {
        new Bot();
    }


    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public Gson getGson() {
        return gson;
    }

    public ServerCache getServerCache() {
        return serverCache;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
}
