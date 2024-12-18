<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="java.util.List;" %>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes-dynattr.tld"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Pembangunan - Kemasukan Maklumat Permohonan Pecah Sempadan</title>
  </head>
  <body>
    <stripes:form beanclass="etanah.view.stripes.PermohonanPembangunanActionBean">
        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Kemasukan Pemohon</legend>
                <div class="content" align="center">
                    <p>Nota : Pemohon yang dibenarkan mestilah pemilik tanah atau pemegang surat kuasa wakil</p>
                    <br>
                </div>
            </fieldset>
        </div>

        <div class="subtitle">
            <fieldset class ="aras1">
                <legend>Carian Surat Kuasa Wakil</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td>ID Surat Kuasa Wakil :</td>
                            <td><s:text name="SK"/></td>
                            <td><s:submit name="Cari" value="Cari" class="btn"/></td>
                        </tr>
                    </table>
                </div>
            </fieldset>
        </div>

       <div class="subtitle">
            <fieldset class ="aras1">
            <legend>Maklumat Surat Kuasa Wakil</legend>
            <div class="content" align="center">
                <table>
                    <tr>
                        <td align="center">
                            <s:submit name="tambah" value="Tambah" class="btn"/> &nbsp;
                            <s:submit name="hapus" value="Hapus" class="btn"/> &nbsp;
                        </td>
                    </tr>
                </table>
                <br>
                <display:table name="Maklumat Surat Kuasa Wakil" id="line" class="tablecloth">
                   <display:column title="Pilih" sortable="true"><div align="center"><s:radio name="radioBtn" value="${line_rowNum}" /></div></display:column>
                   <display:column title="No">${line_rowNum}</display:column>
                   <display:column property="idsuratWakil" title="ID Surat Wakil"/>
                   <display:column property="namaPemberi" title="Nama Pemberi"/>
                   <display:column property="namaPenerima" title="Nama Penerima"/>
                </display:table>
            </div>
         </fieldset>
       </div>

         <div class="subtitle">
            <fieldset class ="aras1">
            <legend>Senarai Pemilik Tanah</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td align="center">
                                <s:submit name="tambah" value="Tambah" class="btn"/> &nbsp;
                                <s:submit name="hapus" value="Hapus" class="btn"/> &nbsp;
                            </td>
                        </tr>
                    </table>
                    <br>
                    <display:table name="Maklumat Pihak Berkepentingan" id="line" class="tablecloth" >
                       <display:column title="Pilih" sortable="true"><div align="center"><s:radio name="radioBtn" value="${line_rowNum}" /></div></display:column>
                       <display:column title="No">${line_rowNum}</display:column>
                       <display:column property="nama" title="Nama"/>
                       <display:column property="noPengenalan" title="No.Pengenalan"/>
                       <display:column property="pihakBerkepentingan" title="Jenis Pihak Berkepentingan"/>
                    </display:table>
                </div>
            </fieldset>
         </div>

         <div class="subtitle">
             <fieldset class="aras1">
                <legend>Pemohon</legend>
                <div class="content" align="center">
                    <table>
                        <tr>
                            <td align="center">
                                <s:submit name="tambah" value="Tambah" class="btn"/> &nbsp;
                                <s:submit name="hapus" value="Hapus" class="btn"/> &nbsp;
                            </td>
                        </tr>
                    </table>
                    <br>
                    <display:table name="Pemohon" id="line" class="tablecloth" >
                       <display:column title="Pilih" sortable="true"><div align="center"><s:radio name="radioBtn" value="${line_rowNum}" /></div></display:column>
                       <display:column title="No">${line_rowNum}</display:column>
                       <display:column property="nama" title="Nama"/>
                       <display:column property="noPengenalan" title="No.Pengenalan"/>
                    </display:table>
                </div>
            </fieldset>
         </div>

         <table  bgcolor="#FF9900" width="100%">
            <tr>
                <td align="right">
                    <s:submit name="simpan" value="Simpan" class="btn"/> &nbsp;
                    <s:reset name="Batal" value="Batal" class="btn"/> &nbsp;
                </td>
            </tr>
         </table>
     </stripes:form>
  </body>
</html>