<%-- 
    Document   : bayaran
    Created on : Apr 9, 2010, 4:09:38 PM
    Author     : nurfaizati
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<div align="center"><table style="width:99.2%" bgcolor="green">
    <tr>
        <td width="100%" height="20" >
            <div style="background-color: green;" align="center">
                <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Pertanyaan Bayaran</font>
            </div>
        </td>
    </tr>
</table></div>

<s:form beanclass="etanah.view.stripes.hasil.BayaranActionBean" id="bayaran">

    <fieldset class="aras1">
        <legend>
            Bayaran
        </legend>
        <div class="instr-fieldset">
            <font color="red">PERINGATAN:</font>Sila Masukan ID Perserahan ATAU Nombor Resit
        </div>&nbsp;

        <div class="instr" align="center">
            <s:errors/>
        </div>

        <p>
            <label for="No Resit">ID Perserahan :</label>
            <s:text name="permohonan.idPermohonan" size="30" onkeyup="this.value=this.value.toUpperCase();"/>
        </p>
      
        <p>
            <label for="No Resit">No Resit Kew.38 :</label>
            <s:text name="resitManual" size="30"/>
        </p>

        <p>
            <label for="No Resit">No Resit :</label>
            <s:text name="dokumenKewangan.idDokumenKewangan" size="30"/>
        </p>

        <table border="0" width="100%">
            <tr>
                <td align="right">
                    <s:submit   name="search" value="Cari" class="btn"/>
                    <s:button name=" " value="Isi Semula" class="btn" onclick="clearText('bayaran');"/>
                </td>
            </tr>
        </table>

    </fieldset>

    <c:if test="${actionBean.idPermohonan ne null}">
        <br>

        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Perserahan
                </legend>

                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.list}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/bayaran" id="line">
                        <display:column property="permohonan.idPermohonan" title="ID Perserahan" class="${line_rowNum}"/>
                        <display:column property="dokumenKewangan.idDokumenKewangan" title="No Resit" class="${line_rowNum}"/>
                        <display:column property="permohonan.kodUrusan.nama" title="Urusan"  />
                        <display:column title="ID Hakmilik" >
                            <c:forEach items="${line.permohonan.senaraiHakmilik}" var="senarai" >
                                <c:out value="${senarai.hakmilik.idHakmilik}" /><br>
                            </c:forEach>
                        </display:column>
                        <%--<display:column property="dokumenKewangan.akaun.hakmilik.idHakmilik" title="ID Hakmilik"  />--%>
                        <display:column property="permohonan.penyerahNama" title="Penyerah"  />
                    </display:table>
                </div>
            </fieldset>


        </div>
    </c:if>
        <%--   --%> <br>
    <c:if test="${actionBean.idDokumenKewangan ne null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Bayaran
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.listT}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/bayaran" id="line">
                        <display:column title="Bil" sortable="true"> <div align="center">${line_rowNum}</div></display:column>
                        <display:column  title="Jenis Transaksi" >
                            ${line.kodTransaksi.kod} - ${line.kodTransaksi.nama}
                            <br>&nbsp;&nbsp;&nbsp;
                            <c:if test="${line.perihal ne null}">
                                <b>${line.perihal}&nbsp;<c:if test="${line.akaunKredit ne null}"><c:if test="${line.akaunKredit.kodAkaun.kod eq 'AKH'}">(-) </c:if></c:if></b>
                            </c:if>
                        </display:column>
                        <c:choose>
                            <c:when test="${line.dokumenKewangan.noRujukanManual ne null}">
                                <display:column property="dokumenKewangan.noRujukanManual" title="No Resit Kew. 38" class="${line_rowNum}"/>
                            </c:when>
                            <c:otherwise>
                                <display:column property="dokumenKewangan.idDokumenKewangan" title="No Resit" class="${line_rowNum}"/>
                            </c:otherwise>
                        </c:choose>
                        <%--<display:column  title="No Resit"  >${line.dokumenKewangan.idDokumenKewangan} </display:column>--%>
                        <display:column title="Tarikh Transaksi" >
                            <fmt:formatDate value="${line.infoAudit.tarikhMasuk}" pattern="dd/MM/yyyy hh:mm aa"/>
                        </display:column>
                        <%--<display:column title="Status Pembayaran"  style="text-align:right">
                            <c:if test="${line.permohonan ne null}">BAYAR</c:if>
                            <c:if test="${line.akaunKredit ne null}">
                                ${line.akaunKredit.baki <= 0 ? "BAYAR" : "BELUM BAYAR" }
                            </c:if>
                        </display:column>--%>
                        <%--<display:column property="amaun" title="Amaun (RM)" class="cawangan${line_rowNum}" format="{0,number, 0.00}" style="text-align:right"/>--%>
                        <display:column title="Amaun (RM)" style="text-align:right">
                            <c:if test="${line.akaunKredit ne null}"><c:if test="${line.akaunKredit.kodAkaun.kod eq 'AKH'}">-</c:if></c:if>
                            <fmt:formatNumber value="${line.amaun}" pattern="#,###,###.00"/>
                        </display:column>
                        <display:footer>
                            <tr>
                                <td colspan="4" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                <td class="number" align="right"><div align="right"><fmt:formatNumber value="${actionBean.jumlahCaj}" pattern="0.00"/></div></td>
                            </tr>
                        </display:footer>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </c:if>

         <br>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Cara Bayaran
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.dkbList}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/hasil/bayaran" id="line">
                        <display:column title="Bil" sortable="true"> <div align="center">${line_rowNum}</div></display:column>

                        <display:column  title="No Resit"  >${line.dokumenKewangan.idDokumenKewangan} </display:column>
                        <display:column title="Cara Bayaran" >${line.caraBayaran.kodCaraBayaran.nama}</display:column>

                        <display:column title="Bank / Agensi Pembayaran">${line.caraBayaran.bank.nama}</display:column>
                        <display:column title="No Rujukan">${line.caraBayaran.noRujukan}</display:column>
                        <display:column property="amaun" title="Amaun (RM)" class="cawangan${line_rowNum}" format="{0,number, 0.00}" style="text-align:right"/>

                             <display:footer>
                                 <tr>
                                     <td colspan="5" align="right"><div align="right"><b>Jumlah (RM) : </b></div></td>
                                     <td class="number" align="right"><div align="right"><fmt:formatNumber value="${line.dokumenKewangan.amaunBayaran}" pattern="0.00"/></div></td>
                                 </tr>
                             </display:footer>
                        
                    </display:table>
                </div>

            </fieldset>


        </div><%--
        --%></c:if>
</s:form>
