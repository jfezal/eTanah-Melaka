<%-- 
    Document   : tambah_siasatan
    Created on : Jan 6, 2011, 11:32:36 AM
    Author     : zadhirul.farihim
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
    function save(){


        var trh_siasatan = document.getElementById("trh").value;
        var perihal = document.getElementById("perihal").value;
        var jam = document.getElementById("jam").value;
        var minit = document.getElementById("minit").value;
     

        if ((trh_siasatan == ""))
        {
            alert('Sila masukkan Tarikh Siasatan');
            document.form1.trh.focus();
            return false;
        }
        else if ((jam == ""))
        {
            alert('Sila pilih jam ');
            document.form1.jam.focus();
            return false;
        }
        else if ((minit == ""))
        {
            alert('Sila pilih minit ');
            document.form1.minit.focus();
            return false;
        }
        else if ((perihal == ""))
        {
            alert('Sila masukkan Perihal Siasatan ');
            document.form1.perihal.focus();
            return false;
        }
        

        else{

            alert('Saving..... ');

            return true;

        }
    }
</script>
<s:form name="form1" beanclass="etanah.view.strata.PenguatkuasaanStrataActionBean">
    <s:messages/>
    <s:errors/>
    
    <div class="subtitle">

        <fieldset class="aras1">

            <legend>Tambah Siasatan</legend>
            <p>
                Yang bertanda(<em>*</em>) adalah wajib diisi.
            </p>
            <p>
                <label><em>*</em>Tarikh :</label><s:text name="trh" id="trh" class="datepicker" onkeyup="validateNumber(this,this.value);"/>
            </p>
            <p>
                <label><em>*</em>Masa :</label>
                <s:select name="jam" style="width:60px;" id="jam">
                    <s:option value="">Jam</s:option>
                    <c:forEach var="i" begin="1" end="12" step="1" varStatus ="status">
                        <s:option value="${i}">${i}</s:option>
                    </c:forEach>
                </s:select>

                <s:select name="minit" style="width:70px;" id="minit">
                    <s:option value="">Minit</s:option>
                    <c:forEach var="i" begin="1" end="59" step="1" varStatus ="status">
                        <s:option value="${i}">${i}</s:option>
                    </c:forEach>
                </s:select>
                <s:select name="masa" style="width:80px;" id="masa">
                    <s:option value="">Pagi</s:option>
                    <s:option value="">Petang</s:option>
                </s:select>
            </p>
            <p>
                <label><em>*</em>Perihal Siasatan :</label><s:textarea name="perihal" id="perihal" rows="5" cols="50"/>
            </p>


            <br>
            <label>&nbsp;</label>
            <%--<s:button name="simpanBangunan" id="simpan" value="Simpan" class="btn" onclick="if(save(this.name,this.form))self.close();"/>--%>
            <s:button name="simpanBangunan" id="simpan" value="Simpan" class="btn" onclick="save();"/>
            <s:button name="close" value="Tutup" onclick="self.close();" class="btn"/>
            <%--<s:button name="tambahBangunan" value="Simpan" class="btn" onclick="if(validateForm())doSubmit(this.form, this.name, 'page_div');"/>--%>
            <br>

            <br>

        </fieldset>
    </div>


</s:form>
