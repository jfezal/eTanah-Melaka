<%-- 
    Document   : cetak16H_2B
    Created on : May 19, 2010, 12:55:20 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<script type="text/javascript">

    function edit1(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?saveDokumen&"+queryString+"&notis.idNotis="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

</script>

<s:form beanclass="etanah.view.stripes.lelong.LelonganAwamActionBean">
    <s:messages/>
    <s:errors/>&nbsp;

    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Perintah Jualan
            </legend>
            <div class="content">
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
                </p>

                <p>
                    <label>Tarikh Lelongan Awam :</label>
                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy"/>&nbsp;
                </p>
                <p>
                    <label>Masa Lelongan Awam :</label>
                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="hh:mm aaa"/>&nbsp;
                </p>
                <p>
                    <label> Tempat :</label>
                    ${actionBean.lelong.tempat}&nbsp;
                </p>
                <p>
                    <label> Harga Rizab (RM):</label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}"/>&nbsp;
                </p>
                <p>
                    <label> Harga Rizab Dalam Perkataan :</label>
                    ${actionBean.lelong.ejaanHarga} RINGGIT MALAYSIA &nbsp;
                </p>
                <%--                <p>
                                    <label> Deposit (RM) :</label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.deposit}"/>&nbsp;
                                </p>--%>
                <%--                <p>
                                    <label> Baki (RM) :</label>
                                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.baki}"/>&nbsp;
                                </p>
                                <p>
                                    <label> Tarikh Akhir Bayar :</label>
                                    <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy"/>&nbsp;
                                </p>--%>



            </div>
        </fieldset>
    </div>

<%--    <div class="content" align="right">
        <p>
            <s:button name="cetak" onclick="edit1(this.form, this.name, 'page_div');" id="btn1" disabled="" value="Cetak Borang 16H" class="longbtn" style=""/>
            <s:button name="cetak" onclick="edit1(this.form, this.name, 'page_div');" id="btn1" disabled="${actionBean.disabled1}" value="Cetak Borang 16H" class="longbtn" style=""/>
        </p>
    </div>--%>
</s:form>
