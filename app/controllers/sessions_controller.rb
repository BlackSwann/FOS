class SessionsController < ApplicationController

  def new
  end

  def create
     session[:password] = Digest::SHA512.hexdigest(params[:password])
     flash[:notice] = "Sucessfully logged in" if admin?
     flash[:notice] = "The password is not valid" unless admin?
     redirect_to root_path
  end

  def destroy
     reset_session
     flash[:notice] = "Successfully logged out"
     redirect_to login_path
  end
end
