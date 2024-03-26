import { UserRespo } from "./UserRespo";

export interface AuthResponse{
  token:  string;
  UserRespo: UserRespo;
}
