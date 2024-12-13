<%--
    Document   : pelelong.jsp
    Created on : 14 april opis hour
    Author     : faidzal
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
--%><script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<script type="text/javascript">
    //    $(document).ready(function() {
    //        $("#tarikhLantikLelong").datepicker();
    //    });
    function dpicker(){
        //    alert("t");
    <%--$("#tarikhLantikLelong").datepicker();--%>
                $("#tarikhLantikLelong").datepicker({dateFormat: 'dd/mm/yy'});
                //       alert("t1");
            }

            function validateNumber(elmnt,content) {
                //if it is character, then remove it..
                if (isNaN(content)) {
                    elmnt.value = removeNonNumeric(content);
                    return;
                }
            }
            function removeNonNumeric( strString )
            {
                var strValidCharacters = "1234567890";
                var strReturn = "";
                var strBuffer = "";
                var intIndex = 0;
                // Loop through the string
                for( intIndex = 0; intIndex < strString.length; intIndex++ )
                {
                    strBuffer = strString.substr( intIndex, 1 );
                    // Is this a number
                    if( strValidCharacters.indexOf( strBuffer ) > -1 )
                    {
                        strReturn += strBuffer;
                    }
                }
                return strReturn;
            }

            function cariLelong(){
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);

                var url = '${pageContext.request.contextPath}/strata/pelelong?cariPelelong';
                window.open(url,'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500, left=" + left + ",top=" + top);



            }
            function save(event, f){

                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.get(url,q,
                function(data){
                    $('#page_div').html(data);

                },'html');
            }

            function removePelelong(){
                var url = '${pageContext.request.contextPath}/strata/pelelong?deletepelelong';
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                },'html');
            }
    
            function validate(){
                var noSyarikat = document.getElementById("noSyarikat").value;
                var nama = document.getElementById("nama").value;
                var alamat1 = document.getElementById("alamat1").value;
                var poskod = document.getElementById("poskod").value;
                var negeri = document.getElementById("negeri").value;


                if ((noSyarikat == ""))
                {
                    alert('Sila masukkan no syarikat ');
                    document.getElementById("noSyarikat").focus();
                    return false;
                }
                else if ((nama == ""))
                {
                    alert('Sila masukkan nama syarikat');
                    document.getElementById("nama").focus();
                    return false;
                }
                else if ((alamat1 == ""))
                {
                    alert('Sila masukkan alamat');
                    document.getElementById("alamat1").focus();
                    return false;
                }
                else if ((poskod == ""))
                {
                    alert('Sila masukkan poskod');
                    document.getElementById("poskod").focus();
                    return false;
                }
                else if ((negeri == ""))
                {
                    alert('Sila pilih negeri');
                    document.getElementById("negeri").focus();
                    return false;
                }
                   
                else{
                    return true;
                }



            }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<s:form beanclass="etanah.view.strata.PelelongActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Syarikat Juru Lelong</legend>
            <p>
                <s:label name="label"> No Syarikat :</s:label><s:text readonly="${actionBean.readOnly}" id="noSyarikat" name="noSyarikat" size="30" maxlength="20" class="normal_text"/>
                </p>
                <p>
                <s:label name="label"> Nama :</s:label><s:text readonly="${actionBean.readOnly}" id="nama"  name="nama" size="30" class="normal_text"/> 
                </p>
                <p>
                <s:label name="label"> Alamat :</s:label><s:text readonly="${actionBean.readOnly}" id="alamat1"  name="alamat1" size="30" class="normal_text"/>
                </p>
                <p>
                <s:label name="label"> &nbsp;</s:label><s:text readonly="${actionBean.readOnly}" id="alamat2" name="alamat2" size="30" class="normal_text"/>
                </p>
                <p>
                <s:label name="label"> &nbsp;</s:label><s:text readonly="${actionBean.readOnly}" id="alamat3" name="alamat3" size="30" class="normal_text"/>
                </p>
                <p>
                <s:label name="label"> &nbsp;</s:label><s:text readonly="${actionBean.readOnly}" id="alamat4" name="alamat4" size="30" class="normal_text"/>
                </p>
                <p>
                <s:label name="label"> Poskod :</s:label><s:text readonly="${actionBean.readOnly}" id="poskod"  name="poskod" maxlength="5" onkeyup="validateNumber(this,this.value);"/>
                </p>
            <c:if test="${!actionBean.readOnly}">
                <p>
                    <s:label id="negeri" name="label"> Negeri :</s:label> <s:select name="negeri" disabled="${actionBean.readOnly}" >
                        <s:option value="">Pilih ...</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodNegeri}" label="nama" value="kod" />
                    </s:select>
                </p>
            </c:if>
            <c:if test="${actionBean.readOnly}">
                <p><s:label name="label"> Negeri :</s:label> <s:text readonly="${actionBean.readOnly}" name="namaNegeri" class="normal_text"/></p>
            </c:if>

            <p>
                <s:label name="label"> No Telefon :</s:label><s:text readonly="${actionBean.readOnly}" id="noTel" name="noTel1" onkeyup="validateNumber(this,this.value);"/>
                </p>
                <p>
                    <label> Tarikh Perlantikan :</label>
                <c:if test="${!actionBean.readOnly}">
                    <s:text formatPattern="dd/MM/yyyy" class="datepicker" onmouseover="dpicker()" onclick="dpicker();"id="tarikhLantikLelong"name="tarikhLantikLelong" size="30"/>
                </c:if>
                <c:if test="${actionBean.readOnly}">
                    <s:text formatPattern="dd/MM/yyyy" readonly="true" name="tarikhLantikLelong" size="30"/>
                </c:if>
            </p>
            <p>
                <s:label name="label">&nbsp;</s:label>
                <c:if test="${!actionBean.readOnly}">
                    <s:button name="kemaskiniPelelong" value="Simpan" onclick="if (validate()){doSubmit(this.form,this.name,'page_div');}" class="btn"/>
                    <s:button name="hapusPelelong" value="Isi Semula" onclick="doSubmit(this.form,this.name,'page_div');" class="btn"/>
                </c:if>
            </p>
        </fieldset>
    </div>
</s:form>
