<%-- 
    Document   : Borang_F
    Created on : Aug 12, 2011, 11:34:32 AM
    Author     : shah
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<s:form beanclass="etanah.view.stripes.pelupusan.BorangFActionBean">
    <div class="subtitle">
        <fieldset class="aras1" align="center">
            <s:messages/>
            <s:errors/>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG' or actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                <legend>
                    Lesen Mencarigali dan Penjelajahan
                </legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                <legend>
                    Lesen Pajakan Lombong
                </legend>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LSTP'}">
                <legend>
                    Lesen Melombong Tuan Punya
                </legend>
            </c:if>
            <p>
                <label>Rujukan Fail :</label>
                ${actionBean.idPermohonan}
                &nbsp;

            </p>
            <p>
                <c:choose>
                    <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'LSTP'}">
                        <label>No. Lesen :</label>
                        ${actionBean.permit.noPermit}
                        &nbsp;
                    </c:when>
                    <c:otherwise>
                        <label>No. Pajakan/Pendaftaran :</label>
                        ${actionBean.permit.noPermit}
                        &nbsp;
                    </c:otherwise>
                </c:choose>
            </p>
            <p>
                <label>No. KPPN :</label>
                <s:text name="noKPPN" size="10"/>
                &nbsp;

            </p>
            <p>
                <label>No. Lot/PT :</label>
                ${actionBean.noLot}
                &nbsp;

            </p>
            <p>
                <label>Bandar/ Kampung/ Mukim :</label>
                ${actionBean.bpm}

                &nbsp;

            </p>
            <p>
                <label>Daerah :</label>
                ${actionBean.daerah}

            </p>
            <p>
                <label>Tarikh Pendaftaran :</label>
                <s:format value="${actionBean.tarikhPendaftaran}" formatPattern="dd/MM/yyyy"/>
                &nbsp;
            </p>
            <p>
                <label>Amaun Bayaran :</label>
                RM <s:format formatPattern="###,###,###.00" value="${actionBean.mohonTuntutKos.amaunTuntutan}" />      
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Mula Lesen :</label>
                <s:text name="permitSahLaku.tarikhPermitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Lesen :</label>
                <s:text name="permitSahLaku.tarikhPermitTamat" class="datepicker" formatPattern="dd/MM/yyyy"/>

            </p>
            <c:if test="${actionBean.kodNegeri eq '05' and actionBean.permohonan.kodUrusan.kod eq 'PJLB' or actionBean.permohonan.kodUrusan.kod eq 'LSTP'}">

                <p>
                    <label><font color="red">*</font>Fi Pengeluaran :</label>
                    <c:set value="${actionBean.mohonHakmilik.fiPengeluaran}" var="a"/>
                    <s:text name="mohonHakmilik.fiPengeluaran" size="10"/>

                </p>
                <p>
                    <label><font color="red">*</font>Sewa Tanah :</label>
                    <c:set value="${actionBean.mohonHakmilik.fiPegangan}" var="b"/>
                    <s:text name="mohonHakmilik.fiPegangan" size="10"/>

                </p>
                <p>
                    <label><font color="red">*</font>Kumpulan Wang Pemulihan :</label>
                    <c:set value="${actionBean.mohonHakmilik.kosInfra}" var="c"/>
                    <s:text name="mohonHakmilik.kosInfra" size="10"/>

                </p>
                <p>
                    <label><font color="red">*</font>20% Royalti :</label>
                    <c:set value="${actionBean.mohonHakmilik.dendaPremium}" var="d"/>
                    <s:text name="mohonHakmilik.dendaPremium" size="10"/>

                </p>
                <p>
                    <label><font color="red">*</font>Total :</label>
                    <c:if test="${actionBean.mohonHakmilik.jumlahPegangan ne null}"> <fmt:formatNumber pattern="#,##0.00" value="${actionBean.mohonHakmilik.jumlahPegangan}"/></c:if>
                    <c:if test="${actionBean.mohonHakmilik.jumlahPegangan eq null}"> - </c:if>
                    <%--<c:set value="${a+b+c+d}" var="e"/>--%>
                    <%--<s:text name="jumlahPegangan" size="4" readonly="true"/>--%>
                    <%--<fmt:formatNumber pattern="#,##0.00"/>--%>

                </p>
            </c:if>
            <br/><br/>
            <center><s:button name="simpan" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/></center>

        </fieldset>
    </div>
</s:form>

