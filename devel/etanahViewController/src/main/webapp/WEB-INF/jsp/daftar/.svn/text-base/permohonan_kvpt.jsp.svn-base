<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Maklumat Permohonan Pemotongan Kaveat Persendirian</title>
    </head>
    <body>
        <script type="text/javascript">

            $(document).ready(function() {

                $('#edit').hide();
            });
            function changevalue(){
      
                $('#edit').show();
                $('#view').hide();

            }
    
            function viewHakmilik(id){
                window.open("${pageContext.request.contextPath}/daftar/rekodPermotongankaveat?viewhakmilikDetail&idHakmilik="+id, "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
            }
        </script>
        <s:messages />
        <s:errors />
        <s:form action="/daftar/rekodPermotongankaveat" >
            <br>
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Pemotongan Kaveat Persendirian</legend>
                <br>
                <p>
                    <label for="idPermohonan">ID Permohonan/ID Perserahan :</label>
                    ${actionBean.permohonan.idPermohonan}
                </p>
                <p>
                    <label for="kodUrusan">Kod Urusan/Urusan :</label>
                    ${actionBean.permohonan.kodUrusan.kod} -
                    ${actionBean.permohonan.kodUrusan.nama}
                </p>
                <p>
                    <label for="tarikhDaftar">Tarikh Perserahan/Permohonan :</label>
                    <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>     <fmt:formatDate pattern="hh:mm aa" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                </p>
                &nbsp;
                <%--<p><label>No Fail :</label>
                    ${actionBean.noFail}
                </p>--%>
            </fieldset>
                <br>
                <fieldset class="aras1">
                    <legend>Maklumat Pihak Berkepentingan</legend>
                    <div class="content" align="center">
                         <display:table class="tablecloth" name="${actionBean.hpkList}"  cellpadding="0" cellspacing="0" id="line">
                             <display:column title="No">${line_rowNum}</display:column>
                             <display:column property="pihak.nama" title="Nama"/>
                             <display:column property="noPengenalan" title="No Pengenalan"/>
                             <display:column title="Alamat">
                                 <c:if test="${line.alamat1 ne null}">
                                     ${line.alamat1}
                                 </c:if>
                                 <c:if test="${line.alamat2 ne null}">
                                     ,
                                 </c:if>${line.alamat2}
                                 <c:if test="${line.alamat3 ne null}">
                                     ,<br/> ${line.alamat3} 
                                 </c:if>                      
                                 <c:if test="${line.alamat4 ne null}">
                                     ,
                                 </c:if>${line.alamat4}                          
                                 <c:if test="${line.poskod ne null}">
                                     ,
                                 </c:if>${line.poskod}                         
                                 <c:if test="${line.negeri.nama ne null}">
                                     ,
                                 </c:if>${line.negeri.nama}
                             </display:column>                           
                             <display:column title="Jenis Pihak">
                                 ${line.jenis.nama}
                             </display:column>                                           
                        </display:table>
                    </div>
                </fieldset ><br>
            <fieldset class="aras1">
                <legend>Maklumat Penyerah</legend><br>
                <div class="content" align="center">
                    <display:table name="${actionBean.permohonan}" id="line1" class="tablecloth">
                        <display:column title="Bil">${line1_rowNum}
                            <%--<s:hidden name="x" class="x${line1_rowNum -1}" value="${line1.idPermohonan}"/>--%>
                        </display:column>
                        <display:column title="Nama" property="penyerahNama"/>
                        <%--<display:column property="penyerahNoPengenalan" title="No. Pengenalan" />--%>
                        <display:column title="Alamat" class="alamat">
                            ${line1.penyerahAlamat1}<c:if test="${line1.penyerahAlamat2 ne null}">,</c:if>
                            ${line1.penyerahAlamat2}<c:if test="${line1.penyerahAlamat3 ne null}">,</c:if>
                            ${line1.penyerahAlamat3}<c:if test="${line1.penyerahAlamat4 ne null}">,</c:if>
                            ${line1.penyerahAlamat4}<c:if test="${line1.penyerahPoskod ne null}">,</c:if>
                            ${line1.penyerahPoskod}<c:if test="${line1.penyerahNegeri.kod ne null}">,</c:if>
                            ${line1.penyerahNegeri.nama}
                        </display:column>
                        <display:column property="penyerahNoTelefon1" title="No. Telefon" />
                        <%--<display:column property="penyerahEmail" title="Alamat E-mail" />--%>
                    </display:table><br><br>
                </div>
            </fieldset><br>
            <fieldset class="aras1">
                <legend>Maklumat Hakmilik</legend>
                <div class="content" align="center">
                    <p><br>
                    <display:table class="tablecloth" name="${actionBean.permohonan.senaraiHakmilik}"  cellpadding="0" cellspacing="0" id="line">
                        
                        <%--<s:hidden name="${actionBean.permohonan.senaraiHakmilik}" id="line2"/>--%>
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column   title="ID Hakmilik" >
                            <a href="#" onclick="viewHakmilik('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a>
                        </display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <display:column title=" Luas "><fmt:formatNumber pattern="#,##0.00" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim"/>
                        <%--<display:table style="display:none" class="tablecloth" name="${actionBean.permohonanHubungan}" id="line2">
                            <display:column title="Kaveat Yang Dipotong" >${line2.idSasar}</display:column> 
                        </display:table>--%>
                                              
                    </display:table>
                    </p><br><br>
                </div>
            </fieldset><br>
            <%--<fieldset class="aras1">
                <legend>
                    Maklumat Notis
                </legend>
                <p>
                    <label>Borang :</label>&nbsp;
                    ${actionBean.notis.dokumenNotis.kodDokumen.kod} - ${actionBean.notis.dokumenNotis.kodDokumen.nama}&nbsp;
                </p>
                <div id="view">
                    <p>
                        <label>Tarikh Notis :</label>&nbsp;
                        ${actionBean.tkhNotis}
                    </p>
                    <p>
                        <label>Tarikh Luput Notis :</label>&nbsp;
                        ${actionBean.tkhTamat}
                    </p>
                    <p>
                        <label>Bilangan Salinan :</label>&nbsp;
                        ${actionBean.notis.bilangan}
                    </p>
                    <p>
                        <label>Tarikh Notis Diserah :</label>&nbsp;
                        ${actionBean.tkhHantar}
                    </p>
                    <p>
                        <label>Penerima Notis :</label>&nbsp;
                        ${actionBean.notis.catatanPenerimaan}
                    </p>
                    <p>
                        <label>Tarikh Tampal Dinotis Awam :</label>&nbsp;
                        ${actionBean.tkhTampal}
                    </p>
                    <p>
                        <label>No Warta :</label>&nbsp;
                        ${actionBean.notis.warta.noRujukan}
                    </p>
                    <p>
                        <label>Tarikh Warta :</label>&nbsp;
                        ${actionBean.tkhWarta}
                    </p>
                      <p>
                        <label>Kaveat Telah Dipotong (Y/T):</label>&nbsp;
                        <c:if test="${actionBean.status eq 'Y'}">
                            Ya
                        </c:if>
                             <c:if test="${actionBean.status eq 'T'}">
                            Tidak
                        </c:if>
                    </p>
                    <br>
                    <table style="margin-left: auto; margin-right: auto;">
                        <tr>
                            <td>&nbsp;</td>
                            <td><div >
                                    <s:button name="update" value="Kemasikini" class="btn" onclick="changevalue()"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="edit">
                    <p>
                        <label>Tarikh Notis :</label>&nbsp;
                        <s:text name="tkhNotis" class="datepicker"/>&nbsp;
                    </p>
                    <p>
                        <label>Tarikh Luput Notis :</label>&nbsp;
                        <s:text name="tkhTamat" class="datepicker"/>&nbsp;
                    </p>
                    <p>
                        <label>Bilangan Salinan :</label>&nbsp;
                        <s:text name="notis.bilangan"/>&nbsp;
                    </p>
                    <p>
                        <label>Tarikh Notis Diserah :</label>&nbsp;
                        <s:text name="notis.tkhHantar" class="datepicker"/>&nbsp;
                    </p>
                    <p>
                        <label>Penerima Notis :</label>&nbsp;
                        <s:text name="notis.catatanPenerimaan" style="width:300px"/>&nbsp;
                    </p>
                    <p>
                        <label>Tarikh Tampal Dinotis Awam :</label>&nbsp;
                        <s:text name="tkhTampal" class="datepicker"/>&nbsp;
                    </p>
                    <p>
                        <label>No Warta :</label>&nbsp;
                        <s:text name="notis.warta.noRujukan"/>&nbsp;
                    </p>
                    <p>
                        <label>Tarikh Warta :</label>&nbsp;
                        <s:text name="tkhWarta" class="datepicker"/>&nbsp;
                    </p>
                    <br>
                    <table style="margin-left: auto; margin-right: auto;">
                        <tr>
                            <td>&nbsp;</td>
                            <td><div >
                                    <s:button name="saveNotisInfo" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>--%>
        </s:form>
    </body>
</html>