import jwtDecode from "jwt-decode";


export function isAuth(token: string | null): boolean {
  return (token?.startsWith("Bearer") && !isExpired(token)) || false;
}

function isExpired(token: string | null): boolean {
  if (token == null) return true;
  const jwt: any = jwtDecode(token);
  return (jwt.exp * 1000) < Date.now();
}
