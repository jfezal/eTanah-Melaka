<%--
    Document   : badanPengurusan.jsp
    Created on : Apr 14, 2011, 12:24:04 AM
    Author     : mohdfaidzal
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%--<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<%--<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>--%>
<script type="text/javascript">
    function tambahPelaksa(){
        var left = (screen.width/2)-(1000/2);
        var top = (screen.height/2)-(150/2);
        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
            var url = '${pageContext.request.contextPath}/strata/pelaksana?tambahPelaksana';
            window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=300, left=" + left + ",top=" + top);


        }
    }
    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.get(url,q,
        function(data){
            $('#page_div').html(data);

        },'html');

    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<%--<s:form name="form1" beanclass="etanah.view.strata.BadanPengurusanActionBean"> By murali--%>
<s:form beanclass="etanah.view.strata.BadanPengurusanActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perbadanan Pengurusan</legend>
            <s:hidden name="idPihak"/>
            <c:if test="${!actionBean.readOnly}">
                <p><label>Nama</label> <s:text name="pengurusanNama" readonly="${actionBean.readOnly}" size="50"/></p>
                <p><label>Alamat</label> <s:text name="pengurusanAlamat1" readonly="${actionBean.readOnly}"  size="50"/></p>
                <p><label>&nbsp;</label> <s:text name="pengurusanAlamat2"readonly="${actionBean.readOnly}"size="50"/></p>
                <p><label>&nbsp;</label> <s:text name="pengurusanAlamat3"readonly="${actionBean.readOnly}"size="50"/></p>
                <p><label>&nbsp;</label> <s:text name="pengurusanAlamat4"readonly="${actionBean.readOnly}"size="50"/></p>
                <p><label>Poskod</label> <s:text name="pengurusanPoskod"readonly="${actionBean.readOnly}"/></p>
                <%--<p><label>Negeri</label> <s:text name="pengurusanKodNegeri"/></p>--%>
                <p>
                    <label>Negeri :</label>
                    <s:select name="pengurusanKodNegeri" disabled="${actionBean.readOnly}" >
                        <s:option>Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
                <p><label>No. Telefon</label> <s:text name="pengurusanTelefon"readonly="${actionBean.readOnly}"/></p>
                <%--<p><label>No. Telefon</label> <s:text name="pengurusanTelefon2"/></p>--%>


                <label>&nbsp;</label><s:button class="btn" name="simpan" value="simpan" onclick="save(this.name, this.form);"/>
            </c:if>
            <c:if test="${actionBean.readOnly}">
                <p><label>Nama :</label> ${actionBean.pengurusanNama}</p>
                <p><label>Alamat :</label> ${actionBean.pengurusanAlamat1}</p>
                <c:if test="${actionBean.pengurusanAlamat2 ne null}">
                    <p><label>&nbsp;</label> ${actionBean.pengurusanAlamat2}</p>
                </c:if>
                <c:if test="${actionBean.pengurusanAlamat3 ne null}">
                    <p><label>&nbsp;</label> ${actionBean.pengurusanAlamat3}</p>
                </c:if>
                <c:if test="${actionBean.pengurusanAlamat4 ne null}">
                    <p><label>&nbsp;</label> ${actionBean.pengurusanAlamat4}</p>
                </c:if>
                <c:if test="${actionBean.pengurusanPoskod ne null}">
                    <p><label>Poskod :</label> ${actionBean.pengurusanPoskod}</p>
                </c:if>
                <c:if test="${actionBean.pengurusanPoskod eq null}">
                    <p><label>Poskod :</label> - </p>
                </c:if>

                <c:if test="${actionBean.pengurusanNamaNegeri ne null}">
                    <p><label>Negeri :</label> ${actionBean.pengurusanNamaNegeri}</p>
                </c:if>
                <c:if test="${actionBean.pengurusanNamaNegeri eq null}">
                    <p><label>Negeri :</label> - </p>
                </c:if>
                <c:if test="${actionBean.pengurusanTelefon ne null}">
                    <p><label>No. Telefon :</label>${actionBean.pengurusanTelefon}</p>
                </c:if>
                <c:if test="${actionBean.pengurusanTelefon eq null}">
                    <p><label>No. Telefon :</label> - </p>
                </c:if>

            </c:if>
        </fieldset>


    </div>
</s:form>
