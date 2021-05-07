import { IModule } from '../interfaces/module';
import { Module, Permission, Action } from "src/app/modules";
import { MatPaginatorIntl } from '@angular/material/paginator';
import { Item } from '../interfaces/item';



export function validateProfile(data: any, modules: Array<IModule>): boolean {
    if (data.validate) {
        return modules.some(p => {
            if (data.module === p.id) {
                return p.submodules.some(sm => sm.id === data.id);
            }
            return false;
        });
    }
    return true;
}

export function buildFormPermissions(modules: Array<Module>, permissions: Array<IModule>): Array<Permission> {
    const options: Array<Permission> = [];
    modules.forEach(module => {
        const option: Permission = {
            id: module.id,
            name: module.title,
            submodules: []
        };
        let pm;
        if (permissions) {
            pm = permissions.find(p => p.id === module.id);
        }

        module.submodules.forEach(submodule => {
            let psm;
            if (pm) {
                psm = pm.submodules.find(pms => pms.id === submodule.id);
            }
            const sm: Action = {
                id: submodule.id,
                name: submodule.name,
                access: psm !== undefined,
                write: psm ? psm.write : false
            };
            option.submodules.push(sm);
        });
        options.push(option);
    });
    return options;
}

export function buildUserPermissions(permissions: Array<Permission>): Array<IModule> {
    const modules: Array<IModule> = [];
    permissions.forEach(permission => {
        const sms = permission.submodules.filter(sm => sm.access);
        if (sms.length) {
            const module: IModule = {
                id: permission.id,
                submodules: []
            };
            sms.forEach(sm => {
                module.submodules.push({
                    id: sm.id,
                    access: sm.access,
                    write: sm.write
                });
            });
            modules.push(module);
        }
    });
    return modules;
}



const spanishRangeLabel = (page: number, pageSize: number, length: number) => {
    if (length == 0 || pageSize == 0) { return `0 de ${length}`; }

    length = Math.max(length, 0);

    const startIndex = page * pageSize;

    // If the start index exceeds the list length, do not try and fix the end index to the end.
    const endIndex = startIndex < length ?
        Math.min(startIndex + pageSize, length) :
        startIndex + pageSize;

    return `${startIndex + 1} - ${endIndex} de ${length}`;
}


export function getSpanishPaginatorIntl() {
    const paginatorIntl = new MatPaginatorIntl();

    paginatorIntl.itemsPerPageLabel = 'Registros por pagina:';
    paginatorIntl.nextPageLabel = 'Siguiente';
    paginatorIntl.previousPageLabel = 'Anterior';
    paginatorIntl.getRangeLabel = spanishRangeLabel;

    return paginatorIntl;
}

export function ItemFinder(id: number, collection: Array<Item>): Item {
    return collection.find(item => item.id === id);
}
