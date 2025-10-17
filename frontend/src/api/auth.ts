import type { SessionResponse } from "../interfaces";
import api from "./axios";

export async function registerUser(
  email: string,
  password: string
): Promise<{ email: string }> {
  const params = new URLSearchParams();
  params.append("email", email);
  params.append("password", password);
  return (await api.post("/RegisterServlet", params)).data;
}

export async function loginUser(
  email: string,
  password: string
): Promise<{ email: string }> {
  const params = new URLSearchParams();
  params.append("email", email);
  params.append("password", password);

  return (await api.post("/LoginServlet", params)).data;
}

export async function checkSession(): Promise<SessionResponse> {
  return (await api.get("/CheckSessionServlet")).data;
}

export async function logoutUser(): Promise<void> {
  return (await api.post("/LogoutServlet")).data;
}
