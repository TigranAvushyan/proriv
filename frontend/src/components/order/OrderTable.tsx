import React, { FC } from "react";
import { useAppActions } from "../../hools/hooks";


const OrderTable: FC = () => {
  const { fetchTable } = useAppActions();

  React.useEffect(() => {
    fetchTable();
  }, []);


  return (
      <div className="ant-table-wrapper">
        <div className="ant-table">
          <div className="ant-table-content">
            <table>
              <thead className="ant-table-thead">
              <tr>
                <th className="ant-table-cell">Наименоание</th>
                <th className="ant-table-cell">Количество</th>
                <th className="ant-table-cell">Обмен</th>
                <th className="ant-table-cell">Возврат</th>
                <th className="ant-table-cell">Цена</th>
                <th className="ant-table-cell">Сумма</th>
              </tr>
              </thead>
              <tbody className="ant-table-tbody">

              </tbody>
            </table>
          </div>
        </div>
      </div>
  );
};

export default OrderTable;
