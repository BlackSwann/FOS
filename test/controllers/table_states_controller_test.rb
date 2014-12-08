require 'test_helper'

class TableStatesControllerTest < ActionController::TestCase
  setup do
    @table_state = table_states(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:table_states)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create table_state" do
    assert_difference('TableState.count') do
      post :create, table_state: { name: @table_state.name }
    end

    assert_redirected_to table_state_path(assigns(:table_state))
  end

  test "should show table_state" do
    get :show, id: @table_state
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @table_state
    assert_response :success
  end

  test "should update table_state" do
    patch :update, id: @table_state, table_state: { name: @table_state.name }
    assert_redirected_to table_state_path(assigns(:table_state))
  end

  test "should destroy table_state" do
    assert_difference('TableState.count', -1) do
      delete :destroy, id: @table_state
    end

    assert_redirected_to table_states_path
  end
end
