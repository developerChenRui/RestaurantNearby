package com.example.chenrui.favoriteitem;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * Created by chenrui on 2018/8/7.
 */

    public class TwoStepOAuth extends DefaultApi10a {
        @Override
        public String getAccessTokenEndpoint() { return null; }

        @Override
        public String getAuthorizationUrl(Token unused) {
            return null;
        }

        @Override
        public String getRequestTokenEndpoint() {
            return null;
        }
    }


