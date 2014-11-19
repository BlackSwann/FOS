class AddTableToOrder < ActiveRecord::Migration
  def change
    add_column :orders, :table, :integer
  end
end
