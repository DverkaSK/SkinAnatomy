package ru.dverkask.skinanatomy.utils;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import ru.dverkask.skinanatomy.SkinAnatomyPlugin;

public enum MessageUtils {
    COMMAND_USAGE("skinanatomy.messages.errors.commandUsage"),
    NO_PERMISSION("skinanatomy.messages.errors.noPermission"),
    NO_PLAYER_SKIN("skinanatomy.messages.errors.noPlayerSkin"),
    SKIN_BY_NICKNAME_NOT_FOUND("skinanatomy.messages.errors.skinByNicknameNotFound"),
    ERROR_LOADING_SKIN("skinanatomy.messages.errors.errorLoadingSkin"),
    DEFAULT_SKIN_NOT_FOUND("skinanatomy.messages.errors.defaultSkinNotFound"),
    HEAD_LOAD_SUCCESS("skinanatomy.messages.loadSuccess.head"),
    BODY_LOAD_SUCCESS("skinanatomy.messages.loadSuccess.body"),
    LEFT_HAND_LOAD_SUCCESS("skinanatomy.messages.loadSuccess.leftHand"),
    RIGHT_HAND_LOAD_SUCCESS("skinanatomy.messages.loadSuccess.rightHand"),
    LEFT_LEG_LOAD_SUCCESS("skinanatomy.messages.loadSuccess.leftLeg"),
    RIGHT_LEG_LOAD_SUCCESS("skinanatomy.messages.loadSuccess.rightLeg"),
    SKIN_URL("skinanatomy.messages.skinUrl");

    @Getter
    private final         String            path;
    private static final SkinAnatomyPlugin PLUGIN = SkinAnatomyPlugin.getInstance();
    MessageUtils(String path) {
        this.path = path;
    }

    public Component getComponent() {
        return MiniMessage.miniMessage().deserialize(PLUGIN.getConfig().getString(path));
    }
}
