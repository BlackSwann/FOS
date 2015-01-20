class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  
 # session :session_key => '_session_id'

  protect_from_forgery with: :exception
  helper_method :admin?

  protected 

  def admin?
     session[:password] == 'foorbar'
  end
end
