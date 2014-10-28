class CreateProducts < ActiveRecord::Migration
  def change
    create_table :products do |t|
      t.string :name
      t.float :price
      t.references :product_type, index: true

      t.timestamps null: false
    end
  end
end
