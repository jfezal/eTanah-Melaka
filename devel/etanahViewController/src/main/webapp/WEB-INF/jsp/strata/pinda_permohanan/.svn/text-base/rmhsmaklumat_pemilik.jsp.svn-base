<%--
    Document   : rmhsmaklumat_pemilik
    Created on : July 23, 2010, 11:30:01 AM
    Author     : Sreenivasa Reddy Munagal
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<%--<s:form beanclass="etanah.view.strata.RMHSPermohonanStrataActionBean" name="form1">--%>
<s:form beanclass="etanah.view.strata.RMHSPermohonanStrataActionBean">
    <s:messages/>
    <s:errors/>

        <div class="subtitle">
            
<%--            <p>
                <label>Id permohonan terdahulu :</label>
                ${idPermohonanTerdahulu}
            </p>--%>

            <fieldset class="aras1">
                <legend>Maklumat Pemilik</legend>
                <p>
                    <label>Nama Projek :</label>
                    ${actionBean.namaProjek}&nbsp;
                </p>
                <p>
                    <label>Cawangan :</label>
                    ${actionBean.cawangan}&nbsp;
                </p>
                <p>
                    <label>Nama Skim :</label>
                    ${actionBean.skim}&nbsp;
                </p>
                <p>
                    <label>Nama Pemilik : </label>
                    ${actionBean.pemilik}&nbsp;
                </p>
                <c:if test="${actionBean.pemilikAlamat1 ne null}">
                <p>
                    <label>Alamat Berdaftar :</label>
                    ${actionBean.pemilikAlamat1}&nbsp;
                </p>
                   </c:if>
                <c:if test="${actionBean.pemilikAlamat2 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pemilikAlamat2}&nbsp;
                </p></c:if>
                <c:if test="${actionBean.pemilikAlamat3 ne null}">
                <p>
                    <label> &nbsp;</label>
                    ${actionBean.pemilikAlamat3}&nbsp;
                </p>
                </c:if>
                <c:if test="${actionBean.pemilikAlamat4 ne null}">
                    <p>
                        <label> &nbsp;</label>
                        ${actionBean.pemilikAlamat4}&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>Poskod :</label>
                    ${actionBean.pemilikPoskod}&nbsp;
                </p>
                <p>
                    <label>Negeri :</label>
                    ${actionBean.pemilikNegeri}&nbsp;
                </p>
                <p>
                    <label>Jenis Kos Rendah :</label>
                    <c:if test="${actionBean.jenisKosRendah eq 'Y'}">
                        Ya
                    </c:if>
                    <c:if test="${actionBean.jenisKosRendah eq 'N'}">
                        Tidak
                    </c:if>
                </p>
            </fieldset>
        </div>
</s:form>