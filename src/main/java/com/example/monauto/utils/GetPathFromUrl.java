package com.example.monauto.utils;

import java.net.URLDecoder;

public class GetPathFromUrl {

    public static String extractPathFromUrl(String downloadUrl) {
        try {
            // Example URL: https://firebasestorage.googleapis.com/v0/b/your-app.appspot.com/o/users%2F123%2Fprofile.jpg?alt=media&token=xyz

            // Remove the base URL
            String prefix = "https://storage.googleapis.com/carte-interactive-e3ecd.appspot.com/";
            String pathWithParams = downloadUrl.replace(prefix, "");

            // Split to remove query parameters
            String encodedPath = pathWithParams.split("\\?")[0];

            // URL decode the path
            return URLDecoder.decode(encodedPath, "UTF-8");
            // Returns: "users/123/profile.jpg"
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Firebase Storage URL");
        }
    }

/*
* https://storage.googleapis.com/carte-interactive-e3ecd.appspot.com/1746812246327_Frederic.jpg?GoogleAccessId=firebase-adminsdk-6efg3@carte-interactive-e3ecd.iam.gserviceaccount.com&Expires=1778348250&Signature=BAne3ijLwo4OTm8eKS7ivZkJ2yrlonvdan78xi3LSSrZFYPwcPSXjBE%2BZKxdkj7OxZ9nGdO2oV743rtG6xDuW%2FFNpLKIc0tACSirUdVIsMrFJkPGUoCG9PbI7xlJE232d6iddXRFLd%2BwbjM%2FJ94HJskvgWAiyVe%2B9crGWqb7xc%2FyW42SL3pvYDDAhqghBwPAggWgAn%2FxP6ljdvptBeZcmwKOAe5CQ2qZWpMfPiKGEpSZN5%2BoTAAUEkbGQvH%2BrrLFxFbGo7ak6v%2Faev6W9BNoLGtbEc2Ua5EadsuwTzUt5yQmbKcEKbbk5NKEG7eTV88kJJYEcCeRALdhBc4CZqLPBg%3D%3D
https://storage.googleapis.com/carte-interactive-e3ecd.appspot.com/1746812251670_Melanie.jpg?GoogleAccessId=firebase-adminsdk-6efg3@carte-interactive-e3ecd.iam.gserviceaccount.com&Expires=1778348252&Signature=HjmlJz02ruRnsoq3xTFi86ZKLgcyoUnqjgLzXTM33McnPmM51nVcIiNWDawmcmfq5ZIy1VTVvRC3rt8bDdyOc3WliabGUUyMykOvtXh%2BiEwHEG%2F%2BhM23dFEjz0eHmheg80Wl%2BF6bASAxEiGFUoOBwY7HqXjkNQ9sp4yVQcB9%2Ffl3mxNdqfXiysUIhdlaJCCrsD391lF%2FGm7ARHg4JSfBnrhYVc%2FenQKb38GPcOqWVdCfw%2Bq1Jn1oMjxdfpTmCGivHJfp%2BihDPubocFyzFuuAQKDlLkcsuk29eX3%2BootBe75FDhIRbYoDQCu8wAQjCjNcPD%2BiEAmg61i6BHM5LEoGvA%3D%3D
* * */
}
