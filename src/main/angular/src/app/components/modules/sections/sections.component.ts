import { AfterViewInit, Component, OnInit, ViewChild, ChangeDetectorRef, OnDestroy } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { SectionsDataSource } from './sections-datasource';
import { ISection } from 'src/app/interfaces/section';
import { SectionService } from 'src/app/services/section.service';
import { ISectionFilter } from "src/app/filters/section-filter";
import { MatDialog } from '@angular/material/dialog';
import { SectionFormComponent } from '../section-form/section-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmationDialogService } from '../../common/ui/confirmation-dialog/confirmation-dialog.service';
import { BaseComponent } from '../base-component/base-component.component';
import { NavigationService } from 'src/app/services/navigation.service';

@Component({
  selector: 'app-sections',
  templateUrl: './sections.component.html',
  styleUrls: ['./sections.component.scss']
})
export class SectionsComponent extends BaseComponent implements OnInit, OnDestroy, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<ISection>;
  dataSource: SectionsDataSource;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['section', 'actions', 'description'];
  filter: ISectionFilter = {
    entity: {
      id: undefined,
      active: undefined,
      description: undefined,
      section: undefined,
    },
    startDate: undefined,
    endDate: undefined,
    hidden: true,
    page: 0,
    rows: 20
  }
  constructor(
    private sectionService: SectionService,
    private changeDetectorRefs: ChangeDetectorRef,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
    private confirmationDialogService: ConfirmationDialogService,
    protected navigationService: NavigationService) {
    super(navigationService);
  }
  ngOnDestroy(): void {
    super.destroy();
  }

  ngOnInit() {

  }

  ngAfterViewInit() {
    this.setFilter();
  }

  public setFilter(searchable?: boolean) {
    if (searchable) {
      this.filter.page = 0;
    }
    this.sectionService.filter(this.filter).then(response => {
      this.dataSource = new SectionsDataSource(response.fields.data);
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
      this.dataSource.count = response.fields.count;
      this.table.dataSource = this.dataSource;
      this.changeDetectorRefs.detectChanges();
    })
  }

  public pageChangeEvent(event: PageEvent) {
    this.filter.rows = event.pageSize;
    this.filter.page = event.pageIndex;
    this.setFilter();
  }

  toggleSearchBar() {
    this.filter.hidden = !this.filter.hidden;
    this.filter.entity = {
      id: undefined,
      active: undefined,
      description: undefined,
      section: undefined,
    }
  }

  public addRow() {
    const entity: ISection = {
      id: 0,
      active: true,
      description: undefined,
      section: undefined
    }
    this.showForm(entity);
  }

  public showForm(entity: ISection) {
    const dialogRef = this.dialog.open(SectionFormComponent, {
      width: '600px',
      disableClose: true,
      data: { entity: entity }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.sectionService.save(result.entity).then(response => {
          this.setFilter();
        });
      }
    });
  }

  delete(entity: ISection): void {
    const dialogRef = this.confirmationDialogService.showConfirmationDialog('¿Desea eliminar el registro?');
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.sectionService.delete(entity).then(response => {
          const snacBarRef = this.snackBar.open(response.message, 'Deshacer', {
            duration: 3000
          });
          this.setFilter();

          snacBarRef.onAction().subscribe(() => {
            this.sectionService.save(entity).then(response => {
              this.setFilter();
            });
          });
        });

      }
    });
  }

}
