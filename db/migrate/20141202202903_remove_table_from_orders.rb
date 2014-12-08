class RemoveTableFromOrders < ActiveRecord::Migration
  def change
    remove_column :orders, :table, :integer
  end
end
