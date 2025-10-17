export interface DashboardProps {
    user: string | null;
}

export interface User{
    email: string;
}

export interface AuthState{
    authenticated: boolean;
    user: string | null;
    loading: boolean;
    error: string | null;
}

export interface UserCredentials{
    email: string;
    password: string;
}

export interface SessionResponse{
    authenticated: boolean;
    email?: string;
}