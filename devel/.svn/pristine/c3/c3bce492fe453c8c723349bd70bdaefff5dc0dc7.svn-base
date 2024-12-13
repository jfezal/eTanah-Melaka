<%--
    Document   : kertas_mmkNS
    Created on : Apr 21, 2011, 6:13:57 PM
    Author     : Murali
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">

    $(document).ready(function() {

        $("#syortolak").hide();
        $("#syorlulus").hide();
        $("#syorlps").hide();

        var v = '${actionBean.fasaPermohonan.keputusan.kod}';
        // alert(v);
        if(v =="SL"){
            $('#syorlps').show();
            $("#syortolak").hide();
            $("#syorlulus").hide();

        }else if(v =="L"){
            $('#syorlulus').show();
            $('#syortolak').hide();
            $('#syorlps').hide();

        }else if(v =="T"){
            $('#syortolak').show();
            $('#syorlulus').hide();
            $('#syorlps').hide();


        }


    <%--$("#syortolak").hide();
    $("#syorlulus").hide();
    $("#syorlps").hide();--%>
        });


           function changeHide(value){
                   //alert(value);
                if(value == "T"){
                    $('#syorlulus').hide();
                    $('#syorlps').hide();
                    $('#syortolak').show();
                }else if(value == "L"){          
                $('#syorlps').hide();
                           $('#syortolak').hide();
                $('#syorlulus').show();
                   }else{
                           $('#syortolak').hide();
                $('#syorlulus').hide();
                $('#syorlps').show();
                   }
           }



//        function addRow(tableid) {
//
//            // alert(tableID);
//            // document.getElementById("dataTable1").value = 1;
//          
//            var table = document.getElementById(tableid);
//            var rowCount = table.rows.length;
//            var row = table.insertRow(rowCount);
//            var tableNo = tableid.substring(9);
//
//            var cell0 = row.insertCell(0);
//            cell0.innerHTML = "<b>"+ tableNo  +"." +(rowCount+1)+"</b>";
//
//            var cell1 = row.insertCell(1);
//            var element1 = document.createElement("textarea");
//            element1.t = "kandungan"+tableNo+""+(rowCount+1);
//            element1.rows = 7;
//            element1.cols = 160;
//            element1.name ="kandungan"+tableNo+""+(rowCount+1);
//            element1.id ="kandungan"+tableNo+""+(rowCount+1);
//            cell1.appendChild(element1);
//
//            document.getElementById("rowCount"+tableNo).value=rowCount+1;
//        }

        function cariPopup(){
                    // alert("search:"+index);
                   // var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?search';
                    var url = '${pageContext.request.contextPath}/pelupusan/kertas_mmkNS?search';
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }

        function cariPopup2(){
            // alert("search:"+index);
           // alert('search');
           <%-- var url = '${pageContext.request.contextPath}/pelupusan/draf_mmkn?showFormCariKodSekatan';--%>
             var url = '${pageContext.request.contextPath}/pelupusan/kertas_mmkNS?showFormCariKodSekatan';
            //alert(url);
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=900,height=700 scrollbars=yes");
        }
        
            function menyimpan(index,i,f)
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
            $.post('${pageContext.request.contextPath}/pelupusan/kertas_mmkNS?simpanKandungan&index='+index+'&kandungan='+kand,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');



        }

        function addRow(index,f)
        {
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/kertas_mmkNS?tambahRow&index='+index,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }
        function deleteRow(idKandungan,f)
        {
            if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                 var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/kertas_mmkNS?deleteRow&idKandungan='+idKandungan,q,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }
        }



</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.stripes.pelupusan.KertasMMKNSActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>
    <s:hidden name="idPermohonan" value="${actionBean.idPermohonan}"/>
    <s:hidden name="editPTD" id="editPTD"/>
     <s:hidden name="editPTG" id="editPTG"/>
     <s:hidden name="openPTD" id="openPTD"/>
     <s:hidden name="openPTG" id="openPTG"/>
    <table width="90%" border="0" >
        <tr>
            <td colspan="4">
                <c:if test="${!actionBean.edit}">
                    <b>Bil. ( ) PTS: ${actionBean.idPermohonan}</b>                    
                </c:if>
                <c:if test="${actionBean.edit}">
                    <b>Bil. ( ) PTS: ${actionBean.idPermohonan}</b>
                </c:if>
                <%--
                <c:if test="${!edit}">
                    <td align="right"><b>Kertas Mesyuarat No: <s:text name="permohonanKertas.nomborRujukan" id="nomborRujukan" size="12"/></b></td>
                </c:if>

                <c:if test="${edit}">
                    <td align="right"><b>Kertas Mesyuarat No: ${actionBean.permohonanKertas.nomborRujukan}</b></td>
                </c:if>
                --%>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div class="subtitle" style="text-transform: uppercase">
                    <fieldset class="aras1">
                        <legend> </legend>
<!--                        <p align="center">
                            <b>(MAJLIS MESYUARAT KERAJAAN PADA <font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" />)</b>
                        </p>-->
                        <div class="content" align="center">                            
                            <table border="0" width="80%" cellspacing="10%" align="center">
                                
                                    <tr><td id="tdLabel" ><b>
                                        <font style="text-transform: uppercase">
                                        <tr><td>
                                            <p><b>
                                                    <%--<c:if test="${!actionBean.edit}">
                                                        <s:textarea rows="6" cols="150" name="tajuk" class="normal_text" style="text-transform: uppercase"/>
                                                    </c:if>--%>
                                                   
<!--                                                        <span style="text-transform: uppercase">${actionBean.tajuk}</span>-->
                                                    <span style="text-transform: uppercase">permohonan daripada ${actionBean.pihakName} untuk memiliki tanah kerajaan secara ${actionBean.mohonHakmilik.kodHakmilikTetap.nama} di bawah seksyen 76 kanun tanah negara 1965 seluas lebihkurang ${actionBean.mohonHakmilik.luasTerlibat}</span>
                                                    
                                              </b></p>
                                             </td>
                                        </tr>
                                        </font>
                                            </b>
                                        </td>
                                    </tr>
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
               Kertas in bertujuan untuk mendapatkan pertimbangan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus mengenai Permohonan daripada ${actionBean.pihakName} seperti berikut :
                <%--
                 <c:if test="${!edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="7" cols="160" name="tujuan">${actionBean.tujuan}</s:textarea></font></td></tr><br>
                </c:if>

                <c:if test="${edit}">
                    <tr><td><font style="text-transform: uppercase">${actionBean.tujuan}</font></td></tr><br>
                </c:if>
                --%>
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>i)</td>
            <td>
                Untuk memiliki tanah Kerajaan secara Hakmilik Tetap / Lesen Pendudukan Sementara
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>ii)</td>
            <td>
                Seluas lebihkurang ${actionBean.mohonHakmilik.luasTerlibat} ${actionBean.mohonHakmilik.kodUnitLuas.nama}
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>iii)</td>
            <td>
                Di ${actionBean.lokasi} mukim ${actionBean.mohonHakmilik.bandarPekanMukimBaru.nama} daerah ${actionBean.permohonan.cawangan.daerah.nama}
            </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td valign="top">&nbsp;</td>
            <td>iv)</td>
            <td>
               Untuk kegunaan ${actionBean.mohonHakmilik.jenisPenggunaanTanah.nama}
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td><b>2.</b></td>
            <td colspan="3"><b> LATAR BELAKANG TANAH</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td width="1%">2.1</td>
            <td>(i)</td>
            <td>Permohonan ini diterima pada <s:format formatPattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/></td>
         </tr>
         <c:if test="${actionBean.openPTG}">
            <tr>
                <td>&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td>(ii)</td>
                <td>Fail diterima oleh Pejabat Tanah dan Galian, Negeri Sembilan pada <s:format formatPattern="dd/MM/yyyy" value="${actionBean.datePTG}"/></td>
             </tr>
             <tr>
                <td>&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td>(iii)</td>
                <td>Mesyuarat Jawatankuasa Tanah Daerah diadakan pada <s:format formatPattern="dd/MM/yyyy" value="${actionBean.dateJKTD}"/></td>
             </tr>
         </c:if>
         <c:if test="${!actionBean.openPTG}">
             <tr>
                <td>&nbsp;</td>
                <td width="1%">&nbsp;</td>
                <td>(ii)</td>
                <td>Mesyuarat Jawatankuasa Tanah Daerah diadakan pada <s:format formatPattern="dd/MM/yyyy" value="${actionBean.dateJKTD}"/></td>
             </tr>
         </c:if>
         <tr>
            <td>&nbsp;</td>
            <td width="1%">2.2</td>
            <td colspan="2"> Tanah yang dimaksudkan seperti bertanda merah di dalam pelan ${actionBean.permohonan.idPermohonan}.</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td width="1%">&nbsp;</td>
            <td colspan="2"> i) Mukim : ${actionBean.mohonHakmilik.bandarPekanMukimBaru.nama}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"> ii) Tempat : ${actionBean.convTempat}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td colspan="2"> iii) Jenis Tanah : ${actionBean.permohonanLaporPelan.kodTanah.nama}</td>
         </tr>
         <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td>iv)</td>
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
            <td width="1%" colspan="3">2.3 Keadaan tanahnya adalah ${actionBean.laporanTanah.kodKeadaanTanah.nama}</td>
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
            <td colspan="2">Perancangan Kerajaan : ${actionBean.permohonanLaporPelan.projekKerajaan}</td>
         </tr>         
         <tr>
            <td>&nbsp;</td>
            <td>2.4</td>
            <td colspan="2">Butir-butir pemohon adalah seperti berikut :</td>
         </tr>
         <tr>
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
         </tr>
         <tr>
             <td colspan="4">&nbsp;</td>
         </tr>
         <tr>
            <td><b>3.</b></td>
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
                <c:if test="${line.permohonanRujukanLuar.ulasan ne null}">
                    ${line.permohonanRujukanLuar.ulasan}
                </c:if>
                <c:if test="${line.permohonanRujukanLuar.ulasan eq null}">
                    TIADA ULASAN
                </c:if>
                    
            </td>
        </tr>
        <c:set var="i" value="${i+1}" />
        </c:forEach>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:if test="${actionBean.openPTD}">
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <td width="1%"><b>4.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENTADBIR TANAH SEREMBAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="4.${i}"/></td>
                        <td colspan="2">
                                <s:textarea  id="kandungan4${i}"name="senaraiLaporanKandungan1[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount4"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTD}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1},this.form)"></s:button>
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
                    <td width="1%"><b>4.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENTADBIR TANAH SEREMBAN</font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="4.${i}"/></td>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan4${i}" id="kandungan2${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount4" value="${fn:length(actionBean.senaraiLaporanKandungan1)}" id="rowCount4"/>
                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
                
                </c:if>
                <c:if test="${actionBean.editPTD}">
                     <tr>
                        <td width="1%"><b>5.</b></td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENTADBIR TANAH SEREMBAN</font></b></div></td>
                     </tr>
                     <tr>
                        <td>&nbsp;</td>
                        <td>Syor :</td>
                        <td colspan="2">
                            <s:select name="kodKepu" onchange="javaScript:changeHide(this.value)">
                                    <s:option value="">--Sila Pilih--</s:option>
                                    <s:option value="T"> Tolak </s:option>
                                    <s:option value="L"> Lulus </s:option>
                                    <s:option value="SL"> Syor LPS </s:option>
                            </s:select>
                        </td>
                     </tr>
                </c:if>
                 <c:if test="${!actionBean.editPTD}">
                     <tr>
                        <td width="1%"><b>5.</b></td>
                        <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENTADBIR TANAH SEREMBAN</font></b></div></td>
                     </tr>
                     <tr>
                        <td>&nbsp;</td>
                        <td>Syor :</td>
                        <td colspan="2">
                            <c:if test="${actionBean.kodKepu eq 'L'}">Lulus</c:if>
                            <c:if test="${actionBean.kodKepu eq 'T'}">Tolak</c:if>     
                            <c:if test="${actionBean.kodKepu eq 'SL'}">Syor LPS</c:if>   
                        </td>
                     </tr>
                </c:if>
        </c:if>
    </table>
    <div id="syortolak" align="center">
        <table width="90%" border="0" >
            <tr>
                <td colspan="4">
                    <c:if test="${actionBean.editPTD}">
                        
                        <font style="text-transform: uppercase"> <s:textarea rows="7" cols="160" name="syortolak" >Pentadbir Tanah Seremban mengesyorkan supaya permohonan ini dikemukakan kepada Majlis Mesyuarat Kerajaan untuk ditolak.</s:textarea></font>
                    </c:if>
                     <c:if test="${!actionBean.editPTD}">
                         <font style="text-transform: uppercase"> Pentadbir Tanah Seremban mengesyorkan supaya permohonan ini dikemukakan kepada Majlis Mesyuarat Kerajaan untuk ditolak
                     </c:if>
                </td>
            </tr>
        </table>
    </div>
    <div id="syorlulus" align="center">
        <table width="90%" border="0" >
            <tr>
                <td colspan="4">
                    Pentadbir Tanah Seremban mengesyorkan supaya permohonan ini diluluskan dengan syarat-syarat seperti berikut :
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Luas</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : Lebihkurang <s:text name="mohonHakmilik.luasTerlibat" id="mohonHakmilik.luasTerlibat" size="20" /> ${actionBean.mohonHakmilik.kodUnitLuas.nama}
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : Lebihkurang ${actionBean.mohonHakmilik.luasTerlibat}  ${actionBean.mohonHakmilik.kodUnitLuas.nama}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Mukim</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:text name="mohonHakmilik.bandarPekanMukimBaru.nama" id="mukim" size="20" />
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : ${actionBean.mohonHakmilik.bandarPekanMukimBaru.nama}
                    </c:if>
                </td>
            </tr>
            <c:if test="${actionBean.editPTD}">
                <tr>
                    <td>&nbsp;</td>
                    <td id="tdLabel">Tempoh</td>
                    <td id="tdDisplay" colspan="2">
                        : <s:radio name="tahunselama" value="99"/>&nbsp;&nbsp;<s:text name="tempohHM" id="tahun" size="10" /> tahun
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay" colspan="2">
                        : <s:radio name="tahunselama" value="0"/>&nbsp;&nbsp;
                          <%--<s:text name="selama" id="selama" size="10" value="0" /> selama-lamanya--%>
                          selama-lamanya
                    </td>
                </tr>
            </c:if>
            <c:if test="${!actionBean.editPTD}">
                <tr>
                    <td>&nbsp;</td>
                    <td id="tdLabel">&nbsp;</td>
                    <td id="tdDisplay" colspan="2">
                        <c:if test="${actionBean.mohonHakmilik.tempohHakmilik eq '999'}">
                            selama-lamanya
                        </c:if>
                         <c:if test="${actionBean.mohonHakmilik.tempohHakmilik ne '999'}">
                            &nbsp;&nbsp;${actionBean.mohonHakmilik.tempohHakmilik} tahun<br>
                        </c:if>
                        
                        <%-- &nbsp;&nbsp; ${actionBean.mohonHakmilik.tempohHakmilik} selama-lamanya--%>
                    </td>
                </tr>
            </c:if>
            
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Jenis Hakmilik Tetap</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:text name="mohonHakmilik.kodHakmilikTetap.nama" id="a" size="20" />
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : ${actionBean.mohonHakmilik.kodHakmilikTetap.nama}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Jenis Hakmilik Sementara</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:text name="mohonHakmilik.kodHakmilikSementara.nama" id="a" size="30" />
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : ${actionBean.mohonHakmilik.kodHakmilikSementara.nama}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Kadar Cukai</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : Mengikut NSPU 7/2005 <%--RM  <s:text name="mohonHakmilik.cukaiPerMeterPersegi" size="17"/> &nbsp; semeter persegi atau sebahagian daripadanya tertakluk kepada minimum <br>
                        &nbsp;&nbsp;RM <s:text id="cukai" name="mohonHakmilik.cukaiPerLot" size="17"/> &nbsp; per lot <br>
                        &nbsp;&nbsp;RM <s:text id="cukai" name="mohonHakmilik.cukaiBaru" size="17"/> &nbsp; sehektar.--%>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : Mengikut NSPU 7/2005 <%--RM  ${actionBean.mohonHakmilik.cukaiPerMeterPersegi} &nbsp; semeter persegi atau sebahagian daripadanya tertakluk kepada minimum <br>
                        &nbsp;&nbsp;RM  ${actionBean.mohonHakmilik.cukaiPerLot} &nbsp; per lot <br>
                        &nbsp;&nbsp;RM  ${actionBean.mohonHakmilik.cukaiBaru} &nbsp; sehektar.--%>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Kadar Premium</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : Mengikut NSPU 25/2002 dan Jabatan Penilaian dan Perkhidmatan Harta
                        <%--<s:textarea name="mohonHakmilik.keteranganKadarPremium" cols="50" id="keteranganKadarPremium"/>--%>
                        <%--<s:text name="mohonHakmilik.kadarPremium" id="kadarPremium" size="20" />--%>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : Mengikut NSPU 25/2002 dan Jabatan Penilaian dan Perkhidmatan Harta <%-- RM ${actionBean.mohonHakmilik.kadarPremium}--%>
                    </c:if>
                </td>
            </tr>
            <%--<tr>
                <td id="tdLabel"><font color="black">Denda Premium</font></td>
                <c:if test="${!edit}">
                    <td id="tdDisplay"> : RM <s:text name="mohonHakmilik.dendaPremium" id="mohonHakmilik.dendaPremiu" size="20" /></td>
                </c:if>

                <c:if test="${edit}">
                    <td id="tdDisplay"> : RM ${actionBean.mohonHakmilik.dendaPremium}</td>
                </c:if>
            </tr>--%>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Bayaran Upah Ukur dan Batu Sempadan</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:select name="mohonHakmilik.agensiUpahUkur">
                          <s:option value="">--Sila Pilih--</s:option>
                          <s:option value="JUP"> Mengikut Jadual </s:option>
                          <s:option value="JUB"> Dilantik Jurukur Berlesen</s:option>
                    </s:select>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : 
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Bayaran Pendaftaran dan Penyediaan Hakmilik</td>
                 <td colspan="2" id="tdDisplay">
                     : Mengikut Peraturan - Peraturan Tanah Negeri
                 </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Kategori</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                         : <s:select name="mohonHakmilik.jenisPenggunaanTanah.kod" id="kategoriTanah">
                            <s:option>Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod"/>
                        </s:select>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        ${actionBean.mohonHakmilik.jenisPenggunaanTanah.nama}
                    </c:if>
                 </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Syarat Nyata</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:textarea name="syaratNyata" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                        <s:hidden name="kod" id="kod"/>
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup()"/>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        :  ${actionBean.mohonHakmilik.syaratNyataBaru.syarat}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Sekatan Kepentingan</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:textarea name="syaratNyata1" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value=""></s:textarea>
                          <s:hidden name="kodSktn" id="kodSktn"/>
                          <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup2()"/>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        :  ${actionBean.mohonHakmilik.sekatanKepentinganBaru.sekatan}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Ingatan</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:select name="aa">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="1"> Dalam </s:option>
                                <s:option value="2"> Luar </s:option>
                          </s:select> Kawasan Rizab Melayu
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        :  Kawasan Rizab Melayu
                    </c:if>
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4">Permohonan lain ditolak.</td>
            </tr>
        </table>
    </div>
    <div id="syorlps" align="center">
        <table width="90%" border="0" >
            <tr>
                <td colspan="4">
                    Pentadbir Tanah Seremban mengesyorkan supaya permohonan ini dikemukakan kepada Majlis Mesyuarat Kerjaan untuk diluluskan <br>
                    secara Lesen Pendudukan Sementara dengan syarat-syarat seperti berikut:
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Luas</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : Lebihkurang <s:text name="mohonHakmilik.luasTerlibat" id="mohonHakmilik.luasTerlibat" size="20" />${actionBean.mohonHakmilik.kodUnitLuas.nama}
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : Lebihkurang ${actionBean.mohonHakmilik.luasTerlibat}  ${actionBean.mohonHakmilik.kodUnitLuas.nama}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Bayaran Lesen</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : RM <s:text name="permohonanTuntutanKos.amaunTuntutan" id="permohonanTuntutanKos.amaunTuntutan" size="20" /> setahun
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : RM ${actionBean.permohonanTuntutanKos.amaunTuntutan}  setahun 
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Maksud Pendudukan</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:select name="kodItemPermit" id="kodItemPermit">
                            <s:option>Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodItemPermit}" label="nama" value="kod"/>
                        </s:select>
                        
                        
                        <%--<s:text name="permohonanPermitItem.kodItemPermit.nama" id="permohonanPermitItem.kodItemPermit.nama" size="20" />--%>
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : ${actionBean.permohonanPermitItem.kodItemPermit.nama}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Lokasi</td>
                <td colspan="2" id="tdDisplay">
                    <c:if test="${actionBean.editPTD}">
                        : <s:text name="mohonHakmilik.lokasi" id="mohonHakmilik.lokasi" size="20" />
                    </c:if>
                    <c:if test="${!actionBean.editPTD}">
                        : ${actionBean.mohonHakmilik.lokasi}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td id="tdLabel">Syarat-syarat lain</td>
                <td colspan="2" id="tdDisplay">
                   : Mengikut yang ditetapkan di dalam Borang.
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="4">Permohonan lain ditolak.</td>
            </tr>
        </table>
    </div>
    <table width="90%" border="0" >
        <tr>
            <td><b>6.</b></td>
            <td colspan="3"><b> PERAKUAN JKTD</b></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td colspan="4">
                <%--<c:if test="${!actionBean.edit}">
                    Mesyuarat Jawatankuasa Tanah Daerah pada &nbsp; <s:text name="permohonanKertas.tarikhSidang" id="tarikhSidang" class="datepicker" formatPattern="dd/MM/yyyy" size="12" />&nbsp; telah mengesyorkan supaya permohonan ini
                    <s:select name="fasaPermohonanJKTD.keputusan.kod" value="${actionBean.fasaPermohonanJKTD.keputusan.kod}">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:option value="L"> Diluluskan </s:option>
                                <s:option value="T"> ditolak </s:option>
                    </s:select> 
                                oleh Majlis Mesyuarat Kerajaan.
                </c:if>--%>
                    Mesyuarat Jawatankuasa Tanah Daerah pada  <%--${actionBean.permohonanKertas.tarikhSidang}--%> ${actionBean.trhksidang} telah mengesyorkan supaya permohonan ini  ${actionBean.fasaPermohonanJKTD.keputusan.nama}  oleh Majlis Mesyuarat Kerajaan.
              
            </td>
        </tr>
         <c:if test="${actionBean.openPTG}">
            <c:if test="${actionBean.editPTG}">
                <tr>
                    <td width="1%"><b>7.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENGARAH TANAH DAN GALIAN <%--NEGERI SEMBILAN--%></font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="7.${i}"/></td>
                        <td colspan="2">
                                <s:textarea  id="kandungan7${i}"name="senaraiLaporanKandunganPTG[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount7" value="${fn:length(actionBean.senaraiLaporanKandunganPTG)}" id="rowCount7"/>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <c:if test="${actionBean.editPTG}">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('7',this.form)"></s:button> 
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('7',${i-1},this.form)"></s:button>
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
                    <td width="1%"><b>7.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">HURAIAN PENGARAH TANAH DAN GALIAN <%--NEGERI SEMBILAN--%></font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="7.${i}"/></td>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan7${i}" id="kandungan7${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount7" value="${fn:length(actionBean.senaraiLaporanKandunganPTG)}" id="rowCount7"/>
                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>
            <c:if test="${actionBean.editPTG}">
                <tr>
                    <td width="1%"><b>8.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENGARAH TANAH DAN GALIAN <%--NEGERI SEMBILAN--%></font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG2}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td valign="top"><c:out value="8.${i}"/></td>
                        <td colspan="2">
                                <s:textarea  id="kandungan8${i}"name="senaraiLaporanKandunganPTG2[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button>
                       </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount8" value="${fn:length(actionBean.senaraiLaporanKandunganPTG2)}" id="rowCount8"/>
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
                    <td><b>8.</b></td>
                    <td colspan="3"><div align="left"><b><font style="text-transform: capitalize">SYOR PENGARAH TANAH DAN GALIAN <%--NEGERI SEMBILAN--%></font></b></div></td>
                </tr>
                <c:set var="i" value="1" />
                <c:forEach items="${actionBean.senaraiLaporanKandunganPTG2}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                        <td><c:out value="8.${i}"/></td>
                        <td colspan="2">
                                ${line.kandungan}<s:hidden name="kandungan8${i}" id="kandungan8${i}"/>
                        </td>
                     </tr>
                <c:set var="i" value="${i+1}" />        
                </c:forEach>
                <s:hidden name="rowCount8" value="${fn:length(actionBean.senaraiLaporanKandunganPTG2)}" id="rowCount8"/>
                
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>
         </c:if>
        <c:if test="${actionBean.editPTD or actionBean.editPTG}">
        <tr>
            <td colspan="4" align="center"><s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></td>            
        </tr>
        </c:if>
    </table>
    <div class="subtitle">
        <fieldset class="aras1">
            
            <div class="content">
                <br>
               
                <table border="0" width="80%">
                    
                    
                    <c:if test="${!edit}">
                    <%--<tr><td><b>8. <u> HURIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</u></b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>

                            <table id ="dataTable8" border="0">
                                <c:set var="i" value="8" />

                                <c:forEach items="${actionBean.senaraiLaporanKandungan1}" var="line">
                                    <tr style="font-weight:bold"><td style="display:none">${line.idKandungan}</td><td></td>
                                        <td><font style="text-transform: uppercase"><s:textarea name="kandungan8${i}" id="kandungan8${i}"  rows="6" cols="150" onkeyup="this.value=this.value.toUpperCase();">${line.kandungan}</s:textarea></font>
                                        </td></tr>
                                        <c:set var="i" value="${i+1}" />

                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                    <tr><td><s:hidden name="rowCount8" value="${i-1}" id="rowCount8"/>&nbsp;</td></tr>
                    <tr>
                        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable8',2)"/>&nbsp;</td>
                    </tr>--%>
                    </c:if>
<!--                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>6. <u> KEPUTUSAN </u></b></td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td><b>6.1 </b>&nbsp;&nbsp;

                            Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.

                        </td></tr>

                    <tr><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td></tr>-->
                    <%--    
                    <label>&nbsp;</label>
                     <c:if test="${!edit}">
                    <tr><td align="center">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td></tr>
                    </c:if>

                    <c:if test="${edit}">
                    <tr><td align="center">
                            <s:button name="simpanhurian" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </td></tr>
                    </c:if>
                    --%>
                </table>
               </div>
        </fieldset>
    </div>
</s:form>
