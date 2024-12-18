<%-- 
    Document   : keputusan_kompaun_dakwa
    Created on : Sep 25, 2012, 11:24:39 AM
    Author     : latifah.iskak
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<c:if test="${multipleOp}">
    <style type="text/css">
        .tablecloth{
            padding:0px;
            width:90%;
        }

        .infoLP{
            float: right;
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;

        }

        .infoHeader{
            font-weight: bold;
            color:#003194;
            font-family:Tahoma;
            font-size: 13px;
            text-align: center;

        }

    </style>
</c:if>
<script type="text/javascript">
    
    
    $(document).ready(function() {
        var bil =  ${fn:length(actionBean.senaraiOksIp)};
        for (var i = 0; i < bil; i++){
            if($("#statusDakwa"+i).val() == "K"){
                document.getElementById("kompaun"+i).checked = true;
            }
            else if($("#statusDakwa"+i).val() == "D"){
                document.getElementById("dakwa"+i).checked = true;
            }else if($("#statusDakwa"+i).val() == "N"){
                document.getElementById("nfa"+i).checked = true;
            }
        }
    });

    function refreshPageCeroboh(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_saksi?refreshpage';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }

    
    function refreshPageSaksiOks(){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/keterangan_saksi?refreshPageSaksiOks';
        $.get(url, function(data){$('#page_div').html(data);},'html');
    }
    
    function viewRecordOP(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?popupViewLaporanOperasi&idOperasi='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
    }
    
    function viewOKS(id){
        var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_orang_disyaki?viewOrangKenaSyak&idOrangKenaSyak='+id;
        window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=600,scrollbars=yes");
    }


    
    function validation() {
        var bil =  ${fn:length(actionBean.senaraiOksIp)};
        var statusCheck = 0;
        for (var i = 0; i < bil; i++){
            if(document.getElementById("kompaun"+i).checked == true || document.getElementById("dakwa"+i).checked == true || document.getElementById("nfa"+i).checked == true){
                statusCheck++;
                //                return false;
            }
        }
        
        if(statusCheck != bil){
            alert("Sila pilih keputusan untuk setiap oks");
            return false;
        }
        return true;
    }
    
    function removeRecord(id){
        if(confirm('Adakah anda pasti untuk hapus?')) {
            var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_kertas_siasatan?deletePermohonan&idPermohonan='+id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }
    
    function doCreatePermohonan() {
        if(confirm('Adakah anda pasti? Sila semak maklumat yang dimasukkan terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            return true;
        }
    }
    
    function doAgih(e, f) {
        if(confirm('Adakah anda pasti? Sila semak tab yang lain terlebih dahulu jika belum semak.')) {
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var q = $(f).formSerialize();
            f.action = f.action + '?' + e + '&' + q;
            f.submit();
        }
    }

</script>
<s:form name="form1" id="form1"  beanclass="etanah.view.penguatkuasaan.KeputusanKompaunDakwaActionBean">
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Senarai Orang Yang Disyaki
            </legend>

            <c:if test="${edit}">
                <div class="instr-fieldset"><br>
                    <c:if test="${fn:length(actionBean.senaraiPermohonanBaru) eq 0}">
                        <font color="red">Makluman : </font> Sila pilih OKS yang hendak dikenakan dakwa/kompaun/nfa. <br>
                        <font color="red">Peringatan : </font> Selepas jana permohonan, anda tidak boleh mengubah maklumat yang telah dimasukkan.
                    </c:if>
                    <c:if test="${fn:length(actionBean.senaraiPermohonanBaru) ne 0}">
                        <font color="red">Makluman : </font> Permohonan baru telah dijana. <br>
                    </c:if>


                </div>
                <div class="content">
                    <c:if test="${fn:length(actionBean.senaraiPermohonanBaru) eq 0}">
                        <p>
                            <label>Id Operasi :</label>
                            <c:if test="${actionBean.operasiPenguatkuasaan.idOperasi ne null}">
                                <u><a class="popup" onclick="viewRecordOP(${actionBean.operasiPenguatkuasaan.idOperasi})">${actionBean.operasiPenguatkuasaan.idOperasi}</a></u>
                            </c:if>
                            <c:if test="${actionBean.operasiPenguatkuasaan.idOperasi eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Tarikh Laporan:</label>
                            <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>&nbsp;</c:if>
                            <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                        </p>
                        <p>
                            <label>Masa Laporan :</label>
                            <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi ne null}">
                                <fmt:formatDate pattern="hh:mm" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}"/>
                                <fmt:formatDate pattern="aaa" value="${actionBean.operasiPenguatkuasaan.tarikhOperasi}" var="time"/>
                                <c:if test="${time eq 'AM'}">PAGI</c:if>
                                <c:if test="${time eq 'PM'}">PETANG</c:if>
                            </c:if>
                            <c:if test="${actionBean.operasiPenguatkuasaan.tarikhOperasi eq null}"> Tiada Data </c:if>
                        </p>
                    </c:if>

                    <br/>

                    <c:if test="${fn:length(actionBean.senaraiPermohonanBaru) eq 0}">
                        <div class="content" align="center">
                            <display:table name="${actionBean.senaraiOksIp}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:70%;">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="Nama">
                                    <a class="popup" onclick="viewOKS(${line.idOrangKenaSyak})">${line.nama}</a>
                                    <input type="hidden" name="idOks${line_rowNum-1}" id="idOks${line_rowNum-1}" value="${line.idOrangKenaSyak}">
                                    <input type="hidden" name="statusDakwa${line_rowNum-1}" id="statusDakwa${line_rowNum-1}" value="${line.statusDakwaan}">
                                </display:column>
                                <display:column title="No.Pengenalan">
                                    <c:if test="${line.noPengenalan eq null}">Tiada data</c:if>
                                    <c:if test="${line.noPengenalan ne null}">${line.noPengenalan}</c:if>
                                </display:column>
                                <display:column title="Keputusan" style="width:30%;">
                                    <input type="radio" name="keputusan${line_rowNum-1}" value="K" id="kompaun${line_rowNum-1}"/> Kompaun &nbsp;&nbsp;
                                    <input type="radio" name="keputusan${line_rowNum-1}" value="D" id="dakwa${line_rowNum-1}"/> Dakwa &nbsp;&nbsp;
                                    <input type="radio" name="keputusan${line_rowNum-1}" value="N" id="nfa${line_rowNum-1}"/> NFA &nbsp;&nbsp;
                                </display:column>
                            </display:table>

                            <p align="center">
                                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                <s:button name="createPermohonan" id="createPermohonan" value="Jana Permohonan" class="longbtn" onclick="if(doCreatePermohonan())doSubmit(this.form, this.name, 'page_div')"/>
                            </p>
                        </div>
                    </c:if>

                    <br><br>
                    <c:if test="${fn:length(actionBean.senaraiPermohonanBaru) ne 0}">
                        <p>
                            <label>No.Permohonan :</label>
                            ${actionBean.permohonan.idPermohonan}
                        </p>
                        <div class="content" align="center">
                            <display:table name="${actionBean.senaraiPermohonanBaru}" id="line" class="tablecloth" cellpadding="0" cellspacing="0" style="width:60%;">
                                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                                <display:column title="ID Kertas Siasatan" property="idPermohonan"/>
                                <display:column title="Maklumat Keputusan">
                                    <c:set value="1" var="count"/>
                                    <c:set value="0" var="status"/>
                                    <c:if test="${fn:length(actionBean.senaraiOksForKompaunDakwa) ne 0}">
                                        <table width="100%" cellpadding="1">
                                            <tr>
                                                <th  width="1%" align="center"><b>Bil</b></th>
                                                <th  width="5%" align="center"><b>Nama OKS</b></th>
                                            </tr>
                                            <c:forEach items="${actionBean.senaraiOksForKompaunDakwa}" var="oks">
                                                <c:if test="${line.idPermohonan eq oks.permohonanAduanOrangKenaSyak.idPermohonan}">
                                                    <tr>
                                                        <td width="5%">${count}</td>
                                                        <td width="20%"> ${oks.nama}&nbsp; </td>
                                                    </tr>
                                                    <c:set value="${count+1}" var="count"/> 
                                                    <c:set value="${oks.statusDakwaan}" var="status"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${status eq 'D'}"><font size="2px;" color="#003194"><b>Keputusan : </b></font>Dakwa</c:if>
                                            <c:if test="${status eq 'K'}"><font size="2px;" color="#003194"><b>Keputusan : </b></font>Kompaun</c:if>
                                            <c:if test="${status eq 'N'}"><font size="2px;" color="#003194"><b>Keputusan : </b></font>NFA</c:if>
                                        </table>
                                    </c:if>
                                </display:column>
                            </display:table>
                            <br>

                        </div>
                        <p align="center">
                            <s:button name="agihTugasan" id="agihTugasanPegawai" value="Selesai" class="btn" onclick="doAgih(this.name, this.form);"/>

                        </p>
                    </c:if>
                </div>




            </c:if>

            <c:if test="${view}">

            </c:if>
        </fieldset>
    </div>

</s:form>
