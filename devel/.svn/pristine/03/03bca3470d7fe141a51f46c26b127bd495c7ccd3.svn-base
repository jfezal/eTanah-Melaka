<%-- 
    Document   : maklumatBantahanMahkamah
    Created on : 08-June-2011, 01:32:00
    Author     : massita
--%>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page contentType="text/html;charset=windows-1252" import="java.util.*" import="java.text.SimpleDateFormat"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<div class="subtitle" id="caw">
    <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.MaklumatBantahanActionBean">
        <s:messages/>
        <div class="instr" align="center">
            <s:errors/>
        </div>
        <s:hidden id="selectedIdHM" name="selectedIdHM" />
        <s:hidden id="selectedIdPP" name="selectedIdPP" />
        <s:hidden name="index" id="index" />
        <s:errors/>
        <s:messages/>
        <fieldset class="aras1"><br /><br />
            <td width="100%" height="20" >
                <div  align="center">
                    MAKLUMAT PERMOHONAN BANTAHAN MAHKAMAH
                </div>
            </td>
            <br/>
            <div>
            </div>
            <br/><br/>

        </fieldset>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Permohonan Terdahulu</legend>
                <br/>
                <table>
                    <tr>
                        <td><label for="nama">Urusan :</label></td>
                        <td><font style="text-transform:uppercase;">${actionBean.permohonanSebelum.kodUrusan.nama}</font></td>
                    </tr>
                    <tr>
                        <td><label for="nama">Nama Permohonan :</label></td>
                        <td>${actionBean.permohonanSebelum.sebab}</td>
                    </tr>
                    <tr>
                        <td><label for="nama">Tarikh Permohonan :</label></td>
                        <td><fmt:formatDate value="${actionBean.permohonanSebelum.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy"/></td>
                    </tr>
                    <tr>
                        <td><label for="nama">Agensi Pemohon :</label></td>
                        <td>${actionBean.pemohon.pihak.nama}</td>
                    </tr>
                    <tr>
                        <td><label>Alamat :</label></td>
                        <td>${actionBean.pemohon.pihak.alamat1}</td>
                    </tr>
                    <tr>
                        <td><label >&nbsp;&nbsp;</label></td>
                        <td>${actionBean.pemohon.pihak.alamat2}</td>
                    </tr>
                    <tr>
                        <td><label >&nbsp;&nbsp;</label></td>
                        <td>${actionBean.pemohon.pihak.alamat3}</td>
                    </tr>
                    <tr>
                        <td><label >&nbsp;&nbsp;</label></td>
                        <td>${actionBean.pemohon.pihak.alamat4}</td>
                    </tr>
                    <tr>
                        <td><label>Poskod :</label></td>
                        <td>${actionBean.pemohon.pihak.poskod}</td>
                    </tr>
                    <tr>
                        <td><label >Negeri :</label></td>
                        <td>${actionBean.pemohon.pihak.negeri.nama}</td>
                    </tr>
                    <tr>
                        <td><label >No. Telefon :</label></td>
                        <td>
                            <c:if test="${actionBean.pemohon.pihak.noTelefon1 ne null}">
                                ${actionBean.pemohon.pihak.noTelefon1}&nbsp;
                            </c:if>
                            <c:if test="${actionBean.pemohon.pihak.noTelefon1 eq null}">
                                - &nbsp;
                            </c:if></td>
                    </tr>

                </table>
                <br/>
                <legend>Maklumat Tanah</legend>
                <br/>
                <table>
                    <tr>
                        <td><label for="nama">No Hakmilik :</label></td>
                        <td>${actionBean.hp.hakmilik.idHakmilik}</td>
                    </tr>
                    <tr>
                        <td><label for="nama">No Lot :</label></td>
                        <td>${actionBean.hp.hakmilik.lot.nama} ${actionBean.hp.hakmilik.noLot}</td>
                    </tr>
                    <tr>
                        <td><label for="nama">Mukim :</label></td>
                        <td>${actionBean.hp.hakmilik.bandarPekanMukim.nama}</td>
                    </tr>

                </table>
                <br/>
                <br/>
                <legend>Maklumat Tuan Tanah</legend><br/>
                <div  align="center">
                    <display:table style="width:50%" class="tablecloth" name="${actionBean.permohonanPihak}" pagesize="3" cellpadding="1" cellspacing="1" requestURI="/pengambilan/maklumatBantahanMahkamah" id="line">
                        <display:column title="Nama" property="pihak.nama" style="text-transform:uppercase;vertical-align:top;" />
                        <display:column title="Jenis Kepentingan" style="vertical-align:top;">${line.jenis.nama}</display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </s:form>
</div>

