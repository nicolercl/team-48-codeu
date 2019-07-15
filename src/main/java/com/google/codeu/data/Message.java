/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.data;

import java.util.UUID;

/**
 * A single message posted by a user.
 */
public class Message {

    private UUID id;
    private String user;
    private String text;
    private long timestamp;
    private String skill;
    private String skillLevel;


    /**
     * Constructs a new {@link Message} posted by {@code user} with {@code text} content. Generates a
     * random ID and uses the current system time for the creation time.
     */
//    public Message(User user, String text,String skill, String skillLevel ) {
//        this(UUID.randomUUID(), user.getName(), text, System.currentTimeMillis(), skill, skillLevel);
//    }
    public Message(User user, String text) {
        this.id = UUID.randomUUID();
        this.user = user.getEmail();
        this.text = text;
        this.timestamp = System.currentTimeMillis();
        this.skill = user.getLearnCategory();
        this.skillLevel = user.getSkillLevel();
    }
    public Message(UUID id, User user, String text, long timestamp,String skill, String skillLevel ) {
        this.id = id;
        this.user = user.getEmail();
        this.text = text;
        this.timestamp = timestamp;
        this.skill = skill;
        this.skillLevel = skillLevel;
    }

    public UUID getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {this.text=text;}

    public long getTimestamp() {
        return timestamp;
    }

    public String getskill() {
        return skill;
    }

    public String getskillLevel() {
        return skillLevel;
    }



}
