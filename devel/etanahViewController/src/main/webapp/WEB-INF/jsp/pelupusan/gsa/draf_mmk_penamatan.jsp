<%-- 
    Document   : draf_mmk_penamatan.jsp
    Created on : 18 August 2011
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>
<script type="text/javascript">


    $(document).ready(function() {
     
        <%--$('#suku').hide();
        $('#ta').hide();--%>
                <%--alert("enter");--%>
         var v = '${actionBean.fasaPermohonan.keputusan.kod}';
         <%--alert(v);--%>
         if(v =="T"){
                   $('#suku').hide();
                   $('#ta').show();
               }else if(v == "L"){
                   $('#suku').show();
                   $('#ta').hide();
               }else{
                   $('#suku').hide();
                   $('#ta').hide();
               }

    });
    function changeHideSuku(value){
    <%-- alert(value);--%>
               if(value == "T"){
                   $('#suku').hide();
                   $('#ta').show();
               }else if(value == "L"){
                   $('#suku').show();
                   $('#ta').hide();
               }else{
                   $('#suku').hide();
                   $('#ta').hide();
               }
           }
<%--           function addRow(tableID) {

               var table = document.getElementById(tableID);
               var rowCount = table.rows.length;
               var row = table.insertRow(rowCount);

               var cell0 = row.insertCell(0);
               cell0.innerHTML = "<b> 4." +(rowCount+1)+"</b>";

               var cell1 = row.insertCell(1);
               var element1 = document.createElement("textarea");
               element1.t = "kandungan"+(rowCount+1);
               element1.rows = 5;
               element1.cols = 150;
               element1.name ="kandungan"+(rowCount+1);
               element1.id ="kandungan"+(rowCount+1);
               cell1.appendChild(element1);

               document.getElementById("rowCount").value=rowCount+1;
           }--%>

   <%--       function addRow(tableid) {

        // alert(tableID);
        // document.getElementById("dataTable1").value = 1;
        var table = document.getElementById(tableid);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);
        var tableNo = tableid.substring(9);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 4." +(rowCount+1)+"</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan"+tableNo+""+(rowCount+1);
        element1.rows = 7;
        element1.cols = 160;
        element1.name ="kandungan"+tableNo+""+(rowCount+1);
        element1.id ="kandungan"+tableNo+""+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount"+tableNo).value=rowCount+1;
    }--%>function menyimpan(index,i,f)
    {
        /*
         * LEGEND : 4 FOR PTD
         */
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 3)
            kand = document.getElementById("kandungan3"+i).value;
        
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==5){
            kand = document.getElementById("kandungan5"+i).value;
        }
        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        if(index==7){
            kand = document.getElementById("kandungan7"+i).value;
        }
        if(index==8){
            kand = document.getElementById("kandungan8"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_GSA?simpanKandungan&index='+index+'&kandungan='+kand,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
  
       
       
    }
    
    function addRow(index,f)
    {
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_GSA?tambahRow&index='+index,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
             var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_GSA?deleteRow&idKandungan='+idKandungan,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
    }
     function cariPopup(){
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_GSA?search';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function cariPopup2(){
        var url = '${pageContext.request.contextPath}/pelupusan/draf_mmk_GSA?showFormCariKodSekatan';
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
    }
    function semakSyor(f,v){
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_mmk_GSA?showsyortolaklulus&syortolaklulus='+v,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMKNGSAActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.idKertas"/>
    <s:hidden name="edit" id="edit"/>
    <s:hidden name="editPTD" id="editPTD"/>
     <s:hidden name="editPTG" id="editPTG"/>
     <s:hidden name="openPTD" id="openPTD"/>
     <s:hidden name="openPTG" id="openPTG"/>
     <s:hidden name="firstTimeOpen" id="firstTimeOpen"/>
    <table width="90%" border="0" >
<!--        <tr>
            <td colspan="4">
                <div class="content" align="right">
                    KM No: <s:hidden name="kmno" id="kmno"/>          
                </div>
            </td>
        </tr>-->
        <tr>
            <td colspan="4">
              <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <legend> </legend>
<!--                        <p align="center">
                            <b>(MESYUARAT JAWATANKUASA TANAH DAERAH PADA <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />)</b>
                        </p>-->
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">
                                
                                    <tr><td id="tdLabel" ><b>
                                        <font style="text-transform: capitalize">
                                        <tr><td>
                                            <p><b>
                                                    <c:if test="${!actionBean.edit}">
                                                        <s:textarea rows="6" cols="150" name="tajuk1" class="normal_text" style="text-transform: uppercase"/>
                                                    </c:if>
                                                    <c:if test="${actionBean.edit}">
                                                        <span style="text-transform: uppercase">${actionBean.tajuk1}</span>
                                                    </c:if>
                                              </b></p>
                                             </td>
                                        </tr>
                                        </font></b></td></tr>
                                <tr><td>&nbsp;</td></tr>
                            </table>                            
                        </div>
                    </fieldset>
              </div>
            </td>
        </tr>
        <tr>
            <td width="1%"><b>1.</b></td>
            <td colspan="3"><div align="left"><b>TUJUAN</b></div></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">1.1</td>
            <td colspan="2">
                Tujuan kertas ini disediakan ialah untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus mengenai permohonan daripada ${actionBean.convNama} seperti berikut;
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td colspan="2">i) Untuk memiliki tanah Kerajaan secara hakmilik tetap.</td>
        </tr>
         <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td colspan="2">ii) Seluas lebih kurang ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}.</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td colspan="2">iii) Di ${actionBean.convTempat}, Mukim ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td colspan="2">iv) Untuk tujuan ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
        </tr>
        <tr>  
            <td colspan="4">
              &nbsp;
            </td>
        </tr>
        <tr>
            <td><b>2.</b></td>
            <td colspan="3"><b> LATAR BELAKANG TANAH</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">2.1</td>
            <td colspan="2"> Permohonan daripada ${actionBean.convNama} diterima pada <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>.</td>
         </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">2.2</td>
            <td colspan="2"> Tanah yang dimaksudkan seperti bertanda merah di dalam pelan ${actionBean.permohonan.idPermohonan}.</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td colspan="2"> i) Di ${actionBean.convTempat}, Mukim ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
            </td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"> ii) Jenis Tanah : ${actionBean.permohonanLaporPelan.kodTanah.nama}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>iii)</td>
            <td>
                <display:table  name="${actionBean.senaraiLaporanKawasan}" id="line9" class="tablecloth">
                        <s:hidden name="" class="${line9_rowNum -1}" value="${line9.idMohonlaporKws}"/>
                        <display:column title="No">${line9_rowNum}</display:column>
                        <display:column title="Jenis Rizab"  property="kodRizab.nama"/>
                        <display:column title="Catatan">
                            <c:if test="${line9.catatan ne null}">
                                ${line9.catatan}
                            </c:if>
                             <c:if test="${line9.catatan eq null}">
                                -
                            </c:if>
                        </display:column>
                        <display:column title="No Warta" property="noWarta"/>
                        <display:column title="Tarikh Warta" property="tarikhWarta" format="{0,date,dd-MM-yyyy}"/>
                        <display:column title="No Pelan Warta" property="noPelanWarta" />                        
                    </display:table>
            </td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td width="1%" colspan="3">2.2 Keadaan tanahnya adalah ${actionBean.laporanTanah.kodKeadaanTanah.nama}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">Keadaan sekeliling tanah adalah seperti berikut :-</td>
         </tr>
         <tr>
            <td colspan="4">&nbsp;</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">Utara : ${actionBean.laporanTanah.sempadanUtaraKegunaan}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">Selatan : ${actionBean.laporanTanah.sempadanSelatanKegunaan}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">Timur : ${actionBean.laporanTanah.sempadanTimurKegunaan}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">Barat : ${actionBean.laporanTanah.sempadanBaratKegunaan}</td>
         </tr>
         <tr>
            <td colspan="4">&nbsp;</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>2.3</td>
            <td colspan="2">Perancangan Kerajaan : <c:if test="${actionBean.permohonanLaporPelan.projekKerajaan eq null}">Tiada</c:if><c:if test="${actionBean.permohonanLaporPelan.projekKerajaan ne null}">${actionBean.permohonanLaporPelan.projekKerajaan}</c:if></td>
         </tr>
         <tr>
             <td colspan="4">&nbsp;</td>
         </tr>
         <tr>
            <td><b>3.</b></td>
            <td colspan="3"><b> BUTIR-BUTIR PEMOHON</b></td>
        </tr>
         <tr>
            <td>&nbsp;</td>
            <td>3.1</td>
            <td colspan="2">Butir-butir pemohon adalah seperti di Lampiran B.</td>
         </tr>
<!--         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2">
                <table class="tablecloth">
                    <tr>
                        <th>Butir-Butir</th><th>Pemohon</th><th>Suami/Isteri</th>
                    </tr>
                    <tr>
                        <th>
                            Nama
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pihak.nama}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.nama}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            No. K/Pengenalan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pihak.noPengenalan}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.noPengenalan}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Alamat
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.alamatPenuhPihak}
                        </td>
                        <td>
                            ${actionBean.alamatPenuhPhbgn}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            T.Lahir
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tarikhLahir}"/>
                        </td>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pemohonHubungan.tarikhLahir}" />
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Tempat Lahir
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pihak.tempatLahir}"/>
                        </td>
                        <td>
                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.pemohonHubungan.tempatLahir}"/>
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Pekerjaan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pemohon.pekerjaan}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.pekerjaan}
                        </td>   
                    </tr>
                    <tr>
                        <th>
                            Pendapatan
                        </th>
                        <%--<td>
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="Y"/>&nbsp;Kerajaan
                            <s:radio name="laporanTanah.sempadanUtaraMilikKerajaan" value="T"/>&nbsp;Milik
                        </td>--%>
                        <td>
                            ${actionBean.pemohon.pendapatan}
                        </td>
                        <td>
                            ${actionBean.pemohonHubungan.gaji}
                        </td>   
                    </tr>
                </table>
            </td>
         </tr>-->
         <tr>
             <td colspan="4">&nbsp;</td>
         </tr>
         <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
            <tr>
                <td><b>4.</b></td>
                <td colspan="3"><b> ULASAN JABATAN TEKNIKAL</b></td>
            </tr>
            <c:set var="i" value="1" />
            <c:forEach items="${actionBean.senaraiLaporanKandunganUtil}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td valign="top"><c:out value="3.${i}"/></td>
                <td colspan="2"><font style="font-weight:bold;">${line.permohonanRujukanLuar.namaAgensi}</font></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">( Ruj. Surat ${line.permohonanRujukanLuar.noRujukan} bertarikh <s:format value="${line.permohonanRujukanLuar.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">
                    <c:if test="${!actionBean.edit}">
                        <s:textarea name="ulasan${i}" id="ulasan${i}"   class="normal_text" rows="6" cols="100" ></s:textarea>
                        <s:hidden name="rowAgensi${i}" value="${line.permohonanRujukanLuar.idRujukan}" id="rowAgensi${i}"/>
                    </c:if>
                    <c:if test="${actionBean.edit}">
                        ${line.permohonanRujukanLuar.ulasan}
                    </c:if>
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
            </c:forEach>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
         </c:if>
         <tr>
             <td><b>
                     <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 4.</c:if>
                     <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 5.</c:if>                     
                 </b>
             </td>
             <td colspan="3"><b> MAKLUMAT PEMILIKAN TANAH PEMOHON</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 4.1</c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 5.1</c:if>
            </td>
            <td colspan="2"><c:if test="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik eq null}">Pemohon ${actionBean.pihak.nama} tidak memiliki tanah.</c:if>
                            <c:if test="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik ne null}">Berikut adalah maklumat pemilikan oleh pemohon ${actionBean.pihak.nama}</c:if>
            </td>
         </tr>
         <c:if test="${actionBean.hakmilikPermohonan.hakmilik.idHakmilik ne null}">
                 <tr>
                <td>&nbsp;</td>
                <td colspan="3">
                        <table class="tablecloth">
                        <tr>
                            <th>Jenis Hakmilik</th><th>No. Hakmilik</th><th>No Lot/Pt/Keluasan</th><th>Daerah</th><th>Mkm/Pk/Bdr</th><th>Kegunaan</th>
                        </tr>
                        <tr>
                            <td>
                                ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.nama}
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.hakmilik.lot.nama}  ${actionBean.hakmilikPermohonan.hakmilik.noLot} / ${actionBean.hakmilikPermohonan.hakmilik.luas} ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama} 
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.hakmilik.daerah.nama}
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}
                            </td>
                            <td>
                                ${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}
                            </td>
                        </tr>
                        </table>
                </td>
             </tr>
             <tr>
                 <td colspan="4">&nbsp;</td>
             </tr>
         </c:if>
         
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 5</c:if>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 6</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENTADBIR TANAH SEREMBAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                       <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="5.${i}"/></td>
                            </c:if>
                          <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                              <td valign="top"><c:out value="6.${i}"/></td>
                          </c:if>
                        <td colspan="2">
                                <s:textarea  id="kandungan6${i}"name="senaraiLaporanKandunganPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganPTD)}" id="rowCount6"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('6',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTD}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${!actionBean.editPTD}">
                <tr>
                    <td width="1%"><b>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 5</c:if>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 6</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENTADBIR TANAH SEREMBAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTD}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="5.${i}"/></td>
                            </c:if>
                          <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                              <td valign="top"><c:out value="6.${i}"/></td>
                          </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan6${i}" id="kandungan6${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount6" value="${fn:length(actionBean.senaraiLaporanKandunganPTD)}" id="rowCount6"/>
                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                
                </c:if>
                 <c:if test="${!actionBean.editPTD}">
                     <tr>
                        <td width="1%">
                            <b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 6</c:if>
                               <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 7</c:if>
                            </b>
                        </td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENTADBIR TANAH SEREMBAN</font></b></div></td>
                     </tr>
<!--                     <tr>
                        <td>&nbsp;</td>
                        <td>Syor</td>
                        <td colspan="2">
                            <c:if test="${actionBean.kodKepu eq 'L'}">Diluluskan</c:if>
                            <c:if test="${actionBean.kodKepu eq 'T'}">Ditolak</c:if>                        
                        </td>
                     </tr>-->
                    <tr>
                        <td colspan="4"></td>
                    </tr>
                </c:if>
          </c:if>         
    </table>
    <table width="90%" border="0" >
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"><b> Keputusan </b> :                                   
            <c:if test="${actionBean.editPTD}">
                <s:radio name="syortolaklulus" value="SL" id="syortolaklulus" onclick="semakSyor(this.form,this.value);"/> Syor Lulus   
                <s:radio name="syortolaklulus" value="ST" id="syortolaklulus" onclick="semakSyor(this.form,this.value);"/> Syor Tolak
            </c:if>
            <c:if test="${!actionBean.editPTD}">
                <c:if test="${actionBean.syortolaklulus eq 'SL'}">
                    Syor Lulus
                </c:if>
                <c:if test="${actionBean.syortolaklulus eq 'ST'}">
                    Syor Tolak
                </c:if>
            </c:if>
         </td>
        </tr>
        <tr>
            <td colspan="4"></td>
        </tr>
        <c:if test="${actionBean.syortolaklulus eq 'SL'}">
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td colspan="2">
                    Oleh yang demikian, Pentadbir Tanah Seremban mengesyorkan supaya permohonan ini dikemukakan kepada Majlis Mesyuarat Kerajaan untuk diluluskan kepada ${actionBean.convNama} dengan syarat-syarat pemberimilikan seperti berikut:-
                </td>
            </tr>
            <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>a) Luas </td>
                <td>
                    : Lebihkurang ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}.
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>b) Tempoh </td>
                <td>
                   :
                   <c:if test="${actionBean.editPTD}">
                       <s:text id="tempoh" name="hakmilikPermohonan.tempohHakmilik" size="10"/> &nbsp; tahun
                   </c:if>
                   <c:if test="${!actionBean.editPTD}">
                       ${actionBean.hakmilikPermohonan.tempohHakmilik} &nbsp; tahun
                   </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>c) Jenis Hakmilik Sementara</td>
                <td> : 
                    <c:if test="${actionBean.editPTD}">
                        <s:select name="kodHmlk" >
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="HSM">Hakmilik Sementara (Mukim)</s:option>
                            <s:option value="HSD">Hakmilik Sementara Daftar</s:option>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        <c:if test="${actionBean.kodHmlk eq 'HSM'}">Hakmilik Sementara (Mukim)</c:if>
                        <c:if test="${actionBean.kodHmlk eq 'HSD'}">Hakmilik Sementara Daftar</c:if>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>d) Jenis Hakmilik Tetap</td>
                <td> : 
                    <c:if test="${actionBean.editPTD}">
                        <s:select name="kodHmlk1" >
                            <s:option value="0">Sila Pilih</s:option>
                            <s:option value="GM">Geran Mukim</s:option>
                            <s:option value="PM">Pajakan Mukim</s:option>
                            <s:option value="GRN">Geran</s:option>
                            <s:option value="PN">Pajakan Negeri</s:option>
                        </s:select>
                    </c:if> 
                    <c:if test="${!actionBean.editPTD}">
                        <c:if test="${actionBean.kodHmlk1 eq 'GM'}">Geran Mukim</c:if>
                        <c:if test="${actionBean.kodHmlk1 eq 'PM'}">Pajakan Mukim</c:if>
                        <c:if test="${actionBean.kodHmlk1 eq 'GRN'}">Geran</c:if>
                        <c:if test="${actionBean.kodHmlk1 eq 'PN'}">Pajakan Negeri</c:if>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>e) Kadar Cukai </td>
                <td> : Mengikut NSPU 7/2005</td>
            </tr>
             <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>f) Kadar Premium </td>
                <td> : Mengikut NSPU 25/2002 dan Jabatan Penilaian dan Perkhidmatan Harta</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>g) Kadar Bayaran Upah Ukur dan Batu Sempadan </td>
                <td> : Mengikut Jadual 
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>h)Kadar Bayaran Pendaftaran dan Penyediaaan Hakmilik Sementara / Tetap </td>
                <td> : Mengikut Peraturan - Peraturan tanah Negeri</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>i)Kategori </td>
                <td> : <c:if test="${actionBean.hakmilikPermohonan.kategoriTanahBaru ne ''}">${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</c:if></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>j)Syarat Nyata </td>
                <td>:
                    <c:if test="${actionBean.editPTD}">
                        <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        ${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}&nbsp;
                    </c:if> 
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>k)Sekatan Kepentingan </td>
                <td> 
                    <c:if test="${actionBean.editPTD}">
                        : <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kodSktn" id="kodSktn"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : ${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}&nbsp;
                    </c:if> 
                </td>
            </tr>
        </c:if>
            <c:if test="${actionBean.syortolaklulus eq 'ST'}">
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        Oleh yang demikian, Pentadbir Tanah Seremban mengesyorkan supaya permohonan ini dikemukakan kepada Majlis Mesyuarat Kerajaan untuk ditolak.
                    </td>
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
    </table>  
    <div id="ta">
    <table width="90%" border="0" >
        <tr>
            <td width="1%">&nbsp;</td>
           <td colspan="3">
                <c:if test="${actionBean.editPTD}">
                     <s:textarea name="syor" rows="7" cols="150"/>
                </c:if>
                <c:if test="${!actionBean.editPTD}">
                    <span>${actionBean.syor}</span> 
                </c:if>
            </td>
        </tr>
    </table>     
    </div>
    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
        <table width="90%" border="0">
        <tr>
            <td><b>7.</b></td>
            <td colspan="3"><b> KEPUTUSAN MESYUARAT JAWATANKUASA TANAH DAERAH</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">7.1</td>
            <td colspan="2"> Mesyuarat Jawatankuasa Tanah Daerah Seremban pada <s:format formatPattern="dd/MM/yyyy" value="${actionBean.fasaPermohonanJKTD.infoAudit.tarikhKemaskini}"/> telah mengesyorkan supaya permohonan ini diluluskan kepada ${actionBean.convNama} oleh Majlis Mesyuarat Mesyuarat Kerajaan Negeri, Negeri Sembilan Darul Khusus.</td>
         </tr>
        </table>
    </c:if>
            
    <c:if test="${actionBean.editPTD}">
        <p align="center">
           <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
        </p>
    </c:if>    
    <c:if test="${actionBean.openPTG}">
        <table width="90%" border="0">
            <c:if test="${actionBean.editPTG}">
                <tr>
                    <td width="1%"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">7</c:if>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">8</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>                        
                        <b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="7.${i}"/></td>
                            </c:if>
                          <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                              <td valign="top"><c:out value="8.${i}"/></td>
                          </c:if>
                        </b>                                            
                        <td colspan="2">
                                <s:textarea  id="kandungan8${i}"name="senaraiLaporanKandunganPTG[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount8" value="${fn:length(actionBean.senaraiLaporanKandunganPTG)}" id="rowCount8"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTG}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('8',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('8',${i-1},this.form)"></s:button>
                        </c:if>
                        <c:if test="${!actionBean.editPTG}">
                            &nbsp;
                        </c:if>
                    </td>                                
                </tr>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                </c:if>
                <c:if test="${!actionBean.editPTG}">
                <tr>
                    <td width="1%"><b>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 7</c:if>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 8</c:if>
                                  </b>
                    </td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                <td valign="top"><c:out value="7.${i}"/></td>
                            </c:if>
                          <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                              <td valign="top"><c:out value="8.${i}"/></td>
                          </c:if>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan8${i}" id="kandungan8${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount8" value="${fn:length(actionBean.senaraiLaporanKandunganPTG)}" id="rowCount8"/>                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>                
                </c:if>
                <c:if test="${actionBean.openPTG}">
                <c:if test="${actionBean.edit}">
                    <c:if test="${actionBean.editPTG}">
                            <tr>
                                <td width="1%"><b>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 8</c:if>
                                      <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 9</c:if>
                                              </b>
                                </td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENGARAH TANAH DAN GALIAN</font></b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganPTG2}" var="line">
                                <tr>
                                    <td>&nbsp;</td>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                        <td valign="top"><c:out value="8.${i}"/></td>
                                    </c:if>
                                  <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                                      <td valign="top"><c:out value="9.${i}"/></td>
                                  </c:if>
                                    <td colspan="2">
                                            <s:textarea  id="kandungan9${i}"name="senaraiLaporanKandunganPTG2[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                            <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                                   </td>
                                 </tr>
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                            <s:hidden name="rowCount9" value="${fn:length(actionBean.senaraiLaporanKandunganPTG2)}" id="rowCount9"/>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <c:if test="${actionBean.editPTG}">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('9',this.form)"></s:button> 
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('9',${i-1},this.form)"></s:button>
                                    </c:if>
                                    <c:if test="${!actionBean.editPTG}">
                                        &nbsp;
                                    </c:if>
                                </td>                                
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                    </c:if> 
                    <c:if test="${!actionBean.editPTG}">
                        <tr>
                                <td width="1%"><b>
                                                  <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 8</c:if>
                                                  <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 9</c:if>
                                              </b>
                                </td>
                                <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENGARAH TANAH DAN GALIAN</font></b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.senaraiLaporanKandunganPTG2}" var="line">
                                <tr>
                                    <td>&nbsp;</td>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}">
                                        <td valign="top"><c:out value="8.${i}"/></td>
                                    </c:if>
                                  <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                                      <td valign="top"><c:out value="9.${i}"/></td>
                                  </c:if>
                                    <td colspan="2">
                                            ${line.kandungan}<s:hidden name="kandungan9${i}" id="kandungan9${i}"/>                                            
                                   </td>
                                 </tr>
                            <c:set var="i" value="${i+1}" />        
                            </c:forEach>
                            <s:hidden name="rowCount9" value="${fn:length(actionBean.senaraiLaporanKandunganPTG2)}" id="rowCount9"/>
                            
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                    </c:if>
                    <tr>
                        <td width="1%"><b>
                              <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 9</c:if>
                              <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 10</c:if>
                                      </b>
                        </td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">KEPUTUSAN</font></b></div></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA'}"> 9.1</c:if>
                              <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}"> 10.1</c:if>
                        </td>
                        <td colspan="2">
                            Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.
                        </td>
                    </tr>
                </c:if>
            </c:if>
        </table>
    </c:if>                
</s:form>
