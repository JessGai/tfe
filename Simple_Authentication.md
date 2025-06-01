### 1. Activate github authentication:

1. Go to your GitHub account, open settings (of your GitHub account, NOT the repository).
2. Open developer settings.
3. Go to 0auth apps and create a new one: Enter an application name and description + homepage    url: http://localhost:8080 and callback url: http://localhost:8081/auth/callback
4. You get a clientID and clientSecret. Save the secret well because you cannot see it again later.
5. Go to https://github.com/login/oauth/authorize?client_id=YOUR_CLIENT_ID&redirect_uri=http://localhost:8080/auth/callback&scope=user:email (replace YOUR_CLIENT_ID by the ID you received in step 4). You WILL get a 404 for now, that's because the callback url must be created in a controller in the application but that can happen later. For now, you get redirected to a url like: http://localhost:8080/auth/callback?code=XXXXXX you can now authenticate with an http call like this:

```json
{
  "client_id": "ghp_your_actual_client_id_here",
  "client_secret": "ghp_your_actual_client_secret_here",
  "code": "authorization_code_from_step_1"
}
```
YOU HAVE TO REGENERATE THE CODE FOR EVERY CALL.  ALTERNATIVE SOLUTIONS: store the generated token and reuse it, OR use personal access token for development

