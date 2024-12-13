<%-- 
    Document   : ringkasan_permohonan_rayuanTolak
    Created on : Feb 18, 2014, 11:13:54 PM
    Author     : khairul.hazwan
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

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
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
    <%-- var cell0 = row.insertCell(0);
     cell0.innerHTML = "<b> 9." +(rowcount+1)+"</b>";--%>
             var cell1 = row.insertCell(0);
             var el = document.createElement('textarea');
             el.name = 'perakuan' + (rowcount+1);
             el.rows = 5;
             el.cols = 150;
             cell1.appendChild(el);
             document.getElementById("rowCount").value=rowcount+1;        
         }

         function deleteRow()
         {
             var j = document.getElementById('rowCount').value;
             if(j == 1){
                 alert("Tiada Boleh Dihapuskan");
                 return false;
             }
             var x= document.getElementById('dataTable').rows[j-1].cells;
             var idKandungan = x[0].innerHTML;

             document.getElementById('dataTable').deleteRow(j-1);
             document.getElementById('rowCount').value= j -1;
    <%--alert(document.getElementById('rowCount').value);--%>
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/ringkasanPermohonanJKBB_RayuanTolak?deleteSingle&idKandungan='
                +idKandungan;
    <%--alert("url:"+url);--%>
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        function addRow2(tableid){
            var table = document.getElementById(tableid);
            var rowcount = table.rows.length;
            var row = table.insertRow(rowcount);
    <%--var cell0 = row.insertCell(0);
    cell0.innerHTML = (rowcount+1)+") ";--%>
            var cell1 = row.insertCell(0);
            var el = document.createElement('textarea');
            el.name = 'tujuan' + (rowcount+1);
            el.rows = 5;
            el.cols = 125;
            cell1.appendChild(el);
            document.getElementById("rowCount2").value=rowcount+1;
        }

        function deleteRow2()
        {
            var j = document.getElementById('rowCount2').value;
            if(j == 1){
                alert("Tiada Boleh Dihapuskan");
                return false;
            }
            var x= document.getElementById('dataTable2').rows[j-1].cells;
            var idKandungan = x[0].innerHTML;

            document.getElementById('dataTable2').deleteRow(j-1);
            document.getElementById('rowCount2').value= j -1;
    <%--alert(document.getElementById('rowCount').value);--%>
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/ringkasanPermohonanJKBB_RayuanTolak?deleteSingle&idKandungan='
                +idKandungan;
    <%--alert("url:"+url);--%>
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        function showReport(){
    <%--window.open("${pageContext.request.contextPath}/pembangunan/melaka/kertasPertimbanganPTG?genReport", "eTanah",
    "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");--%>
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/pembangunan/melaka/ringkasanPermohonanJKBB_RayuanTolak?genReport';
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
    <%--$("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');--%>
                    $.unblockUI();
                },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }
    
        function myconfirm(ele)
        {
            if(confirm('Adakah anda pasti untuk perakuan '+ele.value+" ?"))
                return true;
            return false;
        }
    
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.RingkasanPermohonanRayuanTolakActionBean">
    <s:messages/>
    <s:errors/>  

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>

            <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
            <c:set scope="request" value="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik"/>            

            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="5">                       
                    <tr>
                        <td>
                            <table border="0">
                                <tr>
                                    <c:if test = "${edit}">
                                        <td><b>MESYUARAT JKBB BIL. </b><s:text name="persidangan" size="20" class="normal_text"/></td>
                                        <td width="45%">&nbsp;&nbsp;&nbsp; </td>
                                        <td><b>KERTAS BIL. </b><s:text name="kertasBil" size="20" class="normal_text" maxlength="14"/></td>
                                    </c:if>

                                    <c:if test = "${!edit}">
                                        <td><b>MESYUARAT JKBB BIL. </b> ${actionBean.persidangan}</td>
                                        <td width="45%">&nbsp;&nbsp;&nbsp; </td>
                                        <td><b>KERTAS BIL. </b> ${actionBean.kertasBil}</td>
                                    </c:if>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr><td>
                            <table border="0" width="100%" cellspacing="10">
                                <tr>
                                    <td width="15%">Ruj. Fail</td>
                                    <td width="1%">: </td>
                                    <td>${actionBean.idPermohonanBaru} </td>
                                </tr>
                                <tr>
                                    <td width="15%">Ruj. Fail Sebelum</td>
                                    <td width="1%">: </td>
                                    <td>${actionBean.permohonanBaru.permohonanSebelum.idPermohonan} </td>
                                </tr>
                                <tr>
                                    <td>Tarikh Terima</td>
                                    <td>: </td>
                                    <td>${actionBean.tarikhPermohonan}</td>
                                </tr>
                            </table>
                        </td></tr>

                    <tr><td><b>RINGKASAN PERMOHONAN</b></td></tr>
                    <tr><td>
                            <table border="0" width="100%" cellspacing="10">
                                <tr>
                                    <c:if test = "${edit}">
                                        <td width="20%">Tajuk</td>
                                        <td>: </td>
                                        <td><s:textarea name="tajuk" rows="5" cols="125" class="normal_text"/></td>
                                    </c:if>

                                    <c:if test = "${!edit}">
                                        <td width="20%">Tajuk</td>
                                        <td>: </td>
                                        <td>${actionBean.tajuk}</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td valign="top">Nama Permohonan</td>
                                    <td valign="top"> : </td>
                                    <c:set var="i" value="1"/>
                                    <td><c:forEach items="${senaraiPihak}" var="pihak" end="0">
                                            <c:out value="${pihak.nama}"/>
                                            <c:if test="${fn:length(senaraiPihak) > 1}">
                                                &nbsp; dan ${fn:length(senaraiPihak)-1} yang lain
                                            </c:if>                                                               
                                            <c:set value="${i+1}" var="i"/>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top">Alamat</td>
                                    <td valign="top"> : </td>
                                    <td>
                                        <table border="0" width="80%">
                                            <c:set var="i" value="1"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" end="0">
                                                <tr><td><font size="-1">                                            
                                                        <c:out value="${pihak.alamat1}"/>&nbsp;
                                                        </font></td>
                                                </tr>
                                                <c:if test="${pemohon.pihak.alamat2 ne null}">
                                                    <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.alamat2}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                        <c:if test="${pemohon.pihak.alamat3 ne null}">
                                                    <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.alamat3}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                        <c:if test="${pemohon.pihak.alamat4 ne null}">
                                                    <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.alamat4}"/>&nbsp;</font></td></tr>
                                                        </c:if>
                                                <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.poskod}"/>&nbsp;</font></td></tr>
                                                <tr><td><font size="-1">&nbsp; &nbsp;<c:out value="${pihak.negeri.nama}"/>&nbsp;</font></td></tr>
                                                        <c:set value="${i+1}" var="i"/>
                                                    </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <c:if test = "${edit}">                                   
                                        <td>Lokasi</td>
                                        <td> :</td>
                                        <td><s:textarea name="lokasiTanah" rows="5" cols="125" class="normal_text"/></td>
                                    </c:if>
                                    <c:if test = "${!edit}">                                   
                                        <td>Lokasi</td>
                                        <td> :</td>
                                        <td>${actionBean.lokasiTanah}</td>
                                    </c:if>
                                </tr>

                                <tr>
                                    <td valign="top">Tujuan</td>
                                    <td valign="top"> : </td>
                                    <td><table id ="dataTable2">
                                            <c:if test ="${edit}">
                                                <tr>                                                        
                                                    <td><s:textarea name="tujuan1" id="tujuan1"  rows="5" cols="125" class="normal_text">${line.kandungan}</s:textarea></td>
                                                </tr>
                                                <c:set var="i" value="2" />
                                                <c:forEach items="${actionBean.senaraiTujuan}" var="line" begin="1">
                                                    <tr><td style="display:none">${line.idKandungan}</td>                                                           
                                                        <td><s:textarea name="tujuan${i}" id="tujuan${i}"  rows="5" cols="125" class="normal_text">${line.kandungan}</s:textarea></td>
                                                    </tr>
                                                    <c:set var="i" value="${i+1}" />
                                                </c:forEach>
                                            </c:if>

                                            <c:if test ="${!edit}">
                                                <tr>                                                        
                                                    <td><s:textarea name="tujuan1" id="tujuan1"  rows="5" cols="125" class="normal_text" readonly="readonly">${line.kandungan}</s:textarea></td>
                                                    </tr>
                                                <c:set var="i" value="2" />
                                                <c:forEach items="${actionBean.senaraiTujuan}" var="line" begin="1">
                                                    <tr><td style="display:none">${line.idKandungan}</td>                                                           
                                                        <td><s:textarea name="tujuan${i}" id="tujuan${i}"  rows="5" cols="125" class="normal_text" readonly="readonly">${line.kandungan}</s:textarea></td>
                                                        </tr>
                                                    <c:set var="i" value="${i+1}" />
                                                </c:forEach>
                                            </c:if>
                                        </table>
                                    </td>
                                </tr>
                                <tr><td><s:hidden name="rowCount2"  id="rowCount2"/> </td> </tr>
                                <c:if test = "${edit}">
                                    <tr>
                                        <td colspan="3">
                                            <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                                            <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow2()" /></td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                    </tr>
                    <tr><td><b>ULASAN:</b></td> </tr>
                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <display:table class="tablecloth" name="${actionBean.senaraiRujukanLuar}" cellpadding="0" cellspacing="0" id="tbl1" requestURI="${pageContext.request.contextPath}/pembangunan/melaka/ringkasanPermohonanJKBB_RayuanTolak">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column title="Ulasan Daripada" property="namaAgensi"/>
                                <display:column title="Keputusan">                                       

                                    <c:if test="${tbl1.diSokong eq '1'}">
                                        Disokong                                           
                                    </c:if>
                                    <c:if test="${tbl1.diSokong eq '2'}">
                                        Tidak Disokong                                           
                                    </c:if>
                                    <c:if test="${tbl1.diSokong eq '3'}">
                                        Sokong Bersyarat                                            
                                    </c:if>
                                    <c:if test="${tbl1.diSokong eq '4'}">
                                        Tiada Ulasan                                          
                                    </c:if>
                                    <c:if test="${tbl1.diSokong eq '5'}">
                                        Ada Pindaan                                          
                                    </c:if>
                                    <c:if test="${tbl1.diSokong eq null}">
                                        Tiada Ulasan
                                    </c:if> 
                                </display:column>                                
                            </display:table>

                            <br><br>
                        </td></tr>
                    <tr><td><b>KEPUTUSAN JKBB : </b></td></tr>
                    <tr><td>LULUS / TOLAK / TANGGUH </td></tr>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SBMS'}">
                        <tr>
                            <c:if test = "${edit}">
                                <td>
                                    Nota : <s:textarea name="keputusan" rows="5" cols="125" class="normal_text"/>
                                </td>
                            </c:if>
                            <c:if test = "${edit}">
                                <td>
                                    Nota : ${actionBean.keputusan}
                                </td>
                            </c:if>
                        </tr>
                    </c:if>

                    <%--<tr><td><b>9. PERAKUAN PENGARAH TANAH DAN GALIAN </b></td></tr>--%>
                    <tr><td><s:text name="perakuanPengarah" size="70" readonly="readonly"/></td></tr>                     
                    <tr><td>
                    <tr><td>
                            <table id ="dataTable">
                                <c:if test ="${edit}">
                                    <tr>                                    
                                        <td><s:textarea name="perakuan1" id="perakuan1"  rows="5" cols="150" class="normal_text" readonly="readonly">${line.kandungan}</s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" /> 
                                    <c:forEach items="${actionBean.senaraiKandungan}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>                                        
                                            <td><s:textarea name="perakuan${i}" id="perakuan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </c:if>

                                <c:if test ="${!edit}">
                                    <tr>                                    
                                        <td><s:textarea name="perakuan1" id="perakuan1"  rows="5" cols="150" class="normal_text" readonly="readonly">${line.kandungan}</s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="2" /> 
                                    <c:forEach items="${actionBean.senaraiKandungan}" var="line" begin="1">
                                        <tr><td style="display:none">${line.idKandungan}</td>                                        
                                            <td><s:textarea name="perakuan${i}" id="perakuan${i}"  rows="5" cols="150" class="normal_text" readonly="readonly">${line.kandungan}</s:textarea></td>
                                            </tr>
                                        <c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </c:if>
                            </table>
                        </td>
                    </tr>

                    <tr><td><s:hidden name="rowCount"  id="rowCount"/> </td> </tr>
                    <c:if test = "${edit}">
                        <tr>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('dataTable')"/>&nbsp;
                                <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow()" /></td>
                        </tr>
                    </c:if>

                </table>

                <c:if test ="${edit}">
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>                      
                        <%--<s:button name="genReport" id="report" value="Jana Dokumen" class="btn" onclick="showReport();"/>--%> 
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>