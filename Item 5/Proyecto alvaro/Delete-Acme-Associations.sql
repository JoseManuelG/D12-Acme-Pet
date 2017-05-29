start transaction;

use `Acme-associations`;

revoke all privileges on `Acme-associations`.* from 'acme-user'@'%';
revoke all privileges on `Acme-associations`.* from 'acme-manager'@'%';

drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';

drop database `Acme-associations`;

commit;