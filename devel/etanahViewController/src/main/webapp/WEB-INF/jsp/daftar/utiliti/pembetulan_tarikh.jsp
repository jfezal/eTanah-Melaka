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
    </head>

    <body>
        <script type="text/javascript">
//            $(document).ready( function() {
//
//                $('#tmbh_hakmilik').hide();
//                var len = $(".daerah").length;
//
//                for (var i=0; i<=len; i++){
//                    $('.idHakmilikBaru'+i).click( function() {
//                        window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik="+$(this).text(), "eTanah",
//                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
//                    });
//                }
//            });

            function saveTarikh(val,f){
                if($('#tarikhBaru').val() == ""){
                    alert('Sila Masukkan Tarikh Baru');
                }
                else{
                    f.action = f.action + '?simpanTarikh&idPermohonan=' + val;
                    f.submit();
                }
            }

        </script>
        <s:messages />
        <s:errors />

        <div id="page_div">

            <s:form beanclass="etanah.view.daftar.UtilitiBetulTarikh" name="form1">
                <s:hidden name="permohonan.idPermohonan"></s:hidden>
                <fieldset class="aras1">

                    
                    <legend>Pembetulan Tarikh Penyaksian</legend>
                    <br>
                    <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                        <input type="text" name="idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="checkPermohonan" value="Seterusnya" class="btn" />
                    </p>
                    <br>
                </fieldset>
                <br>
                <c:if test="${actionBean.form}">
                    <fieldset class="aras1">

                        <legend>Pembetulan Tarikh Penyaksian</legend>
                        <br>
                        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                            ${actionBean.permohonan.idPermohonan}
                        </p>

                        <p><label for="tarikhDaftar">Tarikh Penyaksian Lama:</label>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonanUrusan.tarikhSaksi}"/>
                        </p>
                        
                        <p><label>Tarikh Penyaksian Baru :</label>
                        <s:text name="tarikhBaru" class="datepicker"
                            onblur="editDateBlur(this, 'DD/MM/YYYY')"
                            onkeypress="return editDatePre(this, 'DD/MM/YYYY', event)"
                            onkeyup="return editDate(this, 'DD/MM/YYYY', event);"/> <font size="1" color="red">[hh/bb/tttt]</font>
                        </p>
                        &nbsp;
                        
                        <br>
                        
                        <p>
                            <label>&nbsp;</label>
                           
                            <%-- <s:button name="simpanTarikh" value="Simpan" class="btn" onmouseover='this.style.cursor="pointer";' onclick="reload('${actionBean.permohonan.idPermohonan}')"/> --%>
                            <s:button name="simpanTarikh" value="Simpan" class="btn" onclick="saveTarikh('${actionBean.permohonan.idPermohonan}', this.form);"/>
                        </p>
                        
                    </fieldset>
                </div>
            </body>
        </c:if>
    </s:form>
</html>