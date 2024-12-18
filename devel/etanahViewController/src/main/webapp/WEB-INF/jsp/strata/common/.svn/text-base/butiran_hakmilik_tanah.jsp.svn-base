<%-- 
    Document   : butiran_hakmilik_tanah
    Created on : Jul 26, 2011, 4:47:12 PM
    Author     : zadhirul.farihim
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    function p(v){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            $.get("${pageContext.request.contextPath}/strata/penguatkuasaan_strata?maklumatPemilikDetail&idPihak="+v,
            function(data){
                $("#perincianPihak").show();
                $("#perincianPihak").html(data);
                $.unblockUI();
            });
        }
    
</script>


<s:form beanclass="etanah.view.strata.MaklumatTanahActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend> Butiran Hakmilik Tanah</legend>
            <div class="content" align="left">
                <table  border="0">
                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td id="idLabel">Jenis dan No. Hakmilik  </td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.kodHakmilik.nama} &nbsp; ${actionBean.nHakmilik}</td>
                    </tr>

                    <tr><td id="idLabel">No. PT/Lot</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.lot.nama} &nbsp; ${actionBean.nLot}</td>
                    </tr>

                    <tr><td id="idLabel">Bandar/Pekan/Mukim</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.bandarPekanMukim.nama}</td>
                    </tr>

                    <tr><td id="idLabel">Daerah</td>
                        <td>:</td>
                        <td>${actionBean.hakmilik.daerah.nama}</td>
                    </tr>

                    <tr><td id="idLabel">Kategori Tanah</td>
                        <td> :</td>
                        <td>${actionBean.hakmilik.kategoriTanah.nama}</td>

                    </tr>

                    <tr><td id="idLabel">Syarat Nyata Tanah</td>
                        <td> :</td>
                        <td>${actionBean.hakmilik.syaratNyata.syarat}&nbsp;</td>
                    </tr>

                    <tr><td id="idLabel" valign="top">Nama Pemilik Berdaftar</td>
                        <td valign="top"> :</td>
                        <td><c:forEach items="${actionBean.listHakmilikPihak2}" var="list" varStatus="line">

                                <a href="#" title="Sila klik untuk perincian maklumat" onclick="p('${list.pihak.idPihak}');return false;">${list.pihak.nama}</a>
                                <br/>
                            </c:forEach>

                        </td>
                    </tr>

                    <tr><td id="idLabel">Tarikh Didaftarkan</td>
                        <td> :</td>
                        <td><fmt:formatDate type="time" pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;</td>
                    </tr>

                    <tr><td id="idLabel">No. PA</td>
                        <td> :</td>
                        <td><c:if test="${actionBean.hakmilik.noPelan eq null}">Tiada</c:if>
                            <c:if test="${actionBean.hakmilik.noPelan ne null}">${actionBean.hakmilik.noPelan}&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td id="idLabel">No. Syit</td>
                        <td> :</td>
                        <td><c:if test="${actionBean.hakmilik.noLitho eq null}">Tiada</c:if>
                            <c:if test="${actionBean.hakmilik.noLitho ne null}">${actionBean.hakmilik.noLitho}&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td colspan="3">&nbsp;</td></tr>
                    <tr><td colspan="3">
                            <div id="perincianPihak">
                                <p></p>
                            </div>
                        </td>
                    </tr>
                </table>

            </div>
        </fieldset>
    </div>
</s:form>
