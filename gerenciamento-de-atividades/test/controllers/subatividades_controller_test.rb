require 'test_helper'

class SubatividadesControllerTest < ActionController::TestCase
  setup do
    @subatividade = subatividades(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:subatividades)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create subatividade" do
    assert_difference('Subatividade.count') do
      post :create, subatividade: { description: @subatividade.description, duration: @subatividade.duration, location: @subatividade.location, name: @subatividade.name, progress: @subatividade.progress }
    end

    assert_redirected_to subatividade_path(assigns(:subatividade))
  end

  test "should show subatividade" do
    get :show, id: @subatividade
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @subatividade
    assert_response :success
  end

  test "should update subatividade" do
    patch :update, id: @subatividade, subatividade: { description: @subatividade.description, duration: @subatividade.duration, location: @subatividade.location, name: @subatividade.name, progress: @subatividade.progress }
    assert_redirected_to subatividade_path(assigns(:subatividade))
  end

  test "should destroy subatividade" do
    assert_difference('Subatividade.count', -1) do
      delete :destroy, id: @subatividade
    end

    assert_redirected_to subatividades_path
  end
end
