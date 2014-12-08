class AddTableToOrders < ActiveRecord::Migration
  def change
    add_reference :orders, :table, index: true
  end
end
