<%-- 
    Document   : semak_lelongan
    Created on : Mar 8, 2011, 6:39:59 PM
    Author     : mdizzat.mashrom
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    function show_calendar(){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_perintah?showFormB", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1200,height=1200,scrollbars=1");
    }
    
    function viewDetail(id){
        window.open("${pageContext.request.contextPath}/lelong/keputusan_bidaan?viewDetail&idLelong="+id, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=700");
    }
</script>

<s:form beanclass="etanah.view.stripes.lelong.KeputusanPerintahActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>

    <c:if test="${actionBean.PJ eq true}">
        <fieldset class="aras1">
            <legend>
                Maklumat Perintah Jualan 
            </legend>


            <c:if test="${actionBean.lelong.bil ne null}">
                <p>
                    <label>Status lelongan :</label>
                    <c:if test="${actionBean.lelong.bil eq '1'}">
                        Kali Pertama
                    </c:if>
                    <c:if test="${actionBean.lelong.bil eq '2'}">
                        Kali Kedua
                    </c:if>
                    <c:if test="${actionBean.lelong.bil eq '3'}">
                        Kali Ketiga
                    </c:if>
                </p><br>
            </c:if>
            <table>
                <tr>
                    <td>
                        <label>Tarikh Lelongan Awam :</label>
                    </td>
                    <td>
                        ${actionBean.tarikhLelong} &nbsp;
                        &nbsp;
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Masa Lelongan Awam :</label>
                    </td>
                    <td>
                        <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="hh:mm"/>&nbsp;
                        <fmt:formatDate type="time"
                                        pattern="aaa"
                                        value="${actionBean.lelong.tarikhLelong}" var="timeformat"/>
                        <c:if test="${timeformat eq 'AM'}"> PAGI</c:if>
                        <c:if test="${timeformat eq 'PM'}"> PETANG</c:if>
                    </td>
                </tr>
                <tr>
                    <td valign="top">
                        <label>Tempat :</label>
                    </td>
                    <td>
                        ${actionBean.lelong.tempat}
                    </td>
                </tr>

                <tr>
                    <td>
                        <label>Tarikh Akhir Bayaran : </label>
                    </td>
                    <td>
                        ${actionBean.tarikhAkhirBayaran}
                    </td>
                </tr>
            </table>
            <br>

            <%--for PJTA--%>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJTA'}">
                <label>Terbuka Kepada Suku : </label>
                <c:set var="i" value="0"/>
                <c:set var="j" value="0"/>
                <c:forEach items="${actionBean.listSuku}" var="suku">
                    <font style="font-weight: lighter" color="black">- ${suku.kodSuku.nama}</font><p><p/>
                    <label>&nbsp;</label>
                </c:forEach><br>
            </c:if>

            <%--negeri Melaka je--%>
            <c:if test="${actionBean.negori eq true}">
                <p>
                    <label>Jumlah Keseluruhan Hutang : </label>RM
                    <s:format formatPattern="#,##0.00" value="${actionBean.amaunTunggakan}" />
                </p>
                <c:if test="${actionBean.amaunTunggakan2 ne null}">
                    <p>
                        <label>&nbsp;</label>RM
                        <s:format formatPattern="#,##0.00" value="${actionBean.amaunTunggakan2}" />
                    </p>
                </c:if>

                <c:if test="${actionBean.lelong.bil eq '1'}">

                    <p>
                        <label>Pegawai Yang Menjatuhkan Perintah :</label>
                        <font style="text-transform:uppercase;"> ${actionBean.enkuiri.pengguna.nama}</font>&nbsp;
                    </p>
                </c:if>
                <br>

            </c:if>
            <p align="center">
                <c:if test="${same eq false}">
                    <display:table class="tablecloth" name="${actionBean.listLel}" id="line">
                        <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                        <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik" />
                        <display:column title="Harga Rizab" >
                            RM <s:format formatPattern="#,##0.00" value="${line.hargaRizab}" />
                        </display:column>
                        <display:column title="Deposit">
                            RM <s:format formatPattern="#,##0.00" value="${line.deposit}" />
                        </display:column>
                    </display:table>
                </c:if>
                <c:if test="${same eq true}">
                    <display:table class="tablecloth" name="${actionBean.listLelongan}" id="line">
                        <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                        <display:column title="IDHakmilik" value="${actionBean.idHak}"/>
                        <display:column title="Harga Rizab" >
                            RM <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.hargaRizab}" />
                        </display:column>
                        <display:column title="Deposit">
                            RM <s:format formatPattern="#,##0.00" value="${actionBean.enkuiri.deposit}" />
                        </display:column>
                    </display:table>
                </c:if>
            </p></br>
        </fieldset>
    </c:if>

    <c:if test="${fn:length(actionBean.senaraiEnkuiri3)>0}">
        <fieldset class="aras1">
            <legend>
                Sejarah Siasatan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiEnkuiri3}" id="line">
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="Tarikh"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhEnkuiri}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column property="perkara" title="Perkara" class="nama${line_rowNum}"/>
                    <c:if test="${line.status.kod eq 'AK'}">
                        <display:column title="Status" class="" style="text-transform:uppercase;"> </display:column>
                    </c:if>
                    <c:if test="${line.status.kod ne 'AK'}">
                        <display:column property="status.nama" title="Status" class="" style="text-transform:uppercase;"/>
                    </c:if>
                    <display:column property="ulasanPegawai" title="Ulasan" class="nama${line_rowNum}"/>
                </display:table>
            </div>
        </fieldset>
    </c:if>
    <c:if test="${fn:length(actionBean.senaraiLelongan3)>0}">
        <fieldset class="aras1">
            <legend>
                Sejarah Lelongan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.senaraiLelongan3}" id="line" >
                    <display:column title="Bil" sortable="true" >${line_rowNum}</display:column>
                    <display:column title="IDHakmilik" property="hakmilikPermohonan.hakmilik.idHakmilik"/>
                    <display:column title="Tarikh Lelongan Awam"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhLelong}"/></display:column>
                    <display:column title="Masa"><fmt:formatDate pattern="hh:mm aaa" value="${line.tarikhLelong}"/></display:column>
                    <display:column property="tempat" title="Tempat" class="nama${line_rowNum}"/>
                    <display:column title="Harga Rizab (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.hargaRizab}"/>
                        </div></display:column>
                    <display:column title="Deposit (RM)"><div align="right">
                            <fmt:formatNumber pattern="#,##0.00" value="${line.deposit}"/>
                        </div></display:column>
                    <display:column title="Tarikh Akhir Bayaran"><fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhAkhirBayaran}"/></display:column>
                    <!--display:column property="kodStatusLelongan.nama" title="Status" class="" style="text-transform:uppercase;"-->
                    <c:if test="${line.kodStatusLelongan.kod ne 'TB'}">
                        <display:column title="Status" >
                            <a href="#" onclick="viewDetail('${line.idLelong}');return false;">${line.kodStatusLelongan.nama}</a>
                        </display:column>
                    </c:if>

                    <c:if test="${line.kodStatusLelongan.kod eq 'TB'}">
                        <display:column  title="Status" class="">${line.kodStatusLelongan.nama}</display:column>

                    </c:if>
                    <c:if test="${actionBean.negori eq true}">
                        <display:column title="Jurulelong">
                            <c:if test="${line.jurulelong.idJlb eq null}">   
                                -
                            </c:if>
                            <c:if test="${line.jurulelong.idJlb ne null}">   
                                ${line.jurulelong.nama}
                            </c:if>

                        </display:column>
                    </c:if>
                    <c:if test="${actionBean.negori eq false}">
                        <display:column title="Syarikat Jurulelong">
                            <c:if test="${line.sytJuruLelong.idSytJlb eq null}">   
                                -
                            </c:if>
                            <c:if test="${line.sytJuruLelong.idSytJlb ne null}">   
                                ${line.sytJuruLelong.nama}
                            </c:if>

                        </display:column>
                    </c:if>
                    <%--<display:column title="Ulasan" class=""/>--%>
                </display:table>
            </div>
        </fieldset>
    </c:if>
</s:form>