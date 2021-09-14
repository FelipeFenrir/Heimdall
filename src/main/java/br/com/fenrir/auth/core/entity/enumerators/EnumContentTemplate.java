/*
 * Copyright (c) 2021. Fenrir Solucoes em Tecnologia. All rights reserved.
 *  Fenrir Systems, Odin System and All the Programing Code of this softwares are private.
 */

package br.com.fenrir.auth.core.entity.enumerators;

/**
 * <p>
 *     Enum with all Templates of Mail Contents.
 * </p>
 *
 * @author Felipe de Andrade Batista.
 */
public enum EnumContentTemplate {
    /**
     * <p>
     *     User registration confirmation template.
     * </p>
     */
    USER_REGISTRATION("USER_REGISTRATION", "mail/confirm-mail-template"),
    /**
     * <p>
     *      User reset pass confirmation template.
     * </p>
     */
    USER_RESET_PASS("", ""),
    /**
     * <p>
     *      User change pass confirmation template.
     * </p>
     */
    USER_CHANGE_PASS("", ""),
    /**
     * <p>
     *      User change email confirmation template.
     * </p>
     */
    USER_CHANGE_MAIL("", "");

    private final String templateName;
    private final String pathOfTemplate;

    /**
     * <p>
     *     Constructor.
     * </p>
     * @param pathOfTemplate Path of HTML Email Template.
     */
    EnumContentTemplate(String templateName, String pathOfTemplate) {
        this.templateName = templateName;
        this.pathOfTemplate = pathOfTemplate;
    }

    /**
     * <p>
     *     Get Template Name.
     * </p>
     * @return Name of Template.
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * <p>
     *     Get the Path of Template.
     * </p>
     * @return Path of Template.
     */
    public String getPathOfTemplate() {
        return pathOfTemplate;
    }

    /**
     * <p>
     *     Get the Path of Template.
     * </p>
     * @return Path of Template.
     */
    public static String getTemplateByName(String name) {
        return EnumContentTemplate.valueOf(name).getPathOfTemplate();
    }
}
